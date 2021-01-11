/*
 * Copyright (C) 2020 Konrad Renner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kore.wg.boundary.facility.jpa;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import org.kore.wg.control.facility.RealEstateRepository;
import org.kore.wg.control.facility.RealEstateSearchBuilder;
import org.kore.wg.entity.facility.Calibration;
import org.kore.wg.entity.facility.CounterFitting;
import org.kore.wg.entity.facility.Installation;
import org.kore.wg.entity.facility.Manufacturer;
import org.kore.wg.entity.facility.Name;
import org.kore.wg.entity.facility.Owner;
import org.kore.wg.entity.facility.RealEstate;
import org.kore.wg.entity.facility.RealEstateId;

/**
 *
 * @author Konrad Renner
 */
@ApplicationScoped
public class JPARealEstateRepository implements RealEstateRepository {

    private static final Logger LOG = Logger.getLogger(JPARealEstateRepository.class.getName());

    @Inject
    EntityManager em;

    @Override
    public Set<RealEstate> findAll() {

        List<RealEstateEntity> resultList = em.createNamedQuery(RealEstateEntity.FIND_ALL, RealEstateEntity.class).getResultList();

        LOG.info("" + resultList);

        LinkedHashSet<RealEstate> realEstates = new LinkedHashSet<>(resultList.size());
        resultList.stream().map(this::convertToRealEstate).forEach(realEstates::add);

        return realEstates;
    }

    @Override
    public Result find(RealEstateSearchBuilder.Search search) {
        LOG.info("search: " + search + ";");

        long totalCountOfRealEstates = getTotalCountOf(RealEstateEntity.class);

        LOG.info("totalCountOfRealEstates: " + totalCountOfRealEstates);

        SortedSet<RealEstate> realEstates = new TreeSet<>();

        if (totalCountOfRealEstates == 0) {
            LOG.info("found nothing, return empty result");
            return new Result(realEstates, totalCountOfRealEstates);
        }

        TypedQuery<RealEstateEntity> query = em.createQuery(search.query(), RealEstateEntity.class);

        query.setFirstResult((int) search.area().start())
                .setMaxResults((int) search.area().maxCount())
                .getResultStream()
                .map(this::convertToRealEstate)
                .forEach(realEstates::add);

        Result result = new Result(realEstates, totalCountOfRealEstates);
        LOG.info(result.toString());
        return result;
    }

    <T> long getTotalCountOf(Class<T> entity) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<T> root = query.from(entity);
        Expression<Long> countExpression = builder.count(root);
        query.select(countExpression);
        TypedQuery<Long> typedQuery = em.createQuery(query);

        return typedQuery.getSingleResult().longValue();
    }

    RealEstate convertToRealEstate(RealEstateEntity entity) {
        RealEstateId id = new RealEstateId(entity.getCadastralTownshipNumber(), entity.getEstateId(), entity.getDepositNumber());
        LinkedHashSet<Owner> owner = new LinkedHashSet<>();
        entity.getOwners().stream().map(this::convertToOwner).forEach(owner::add);
        LinkedHashSet<CounterFitting> fittings = new LinkedHashSet<CounterFitting>();
        entity.getCounterfittings().stream().map(this::convertToCounterFitting).forEach(fittings::add);
        return new RealEstate(id, owner, fittings);
    }

    Owner convertToOwner(OwnerEntity entity) {
        return new Owner(UUID.fromString(entity.getId()), new Name(entity.getFirstname(), entity.getLastname()));
    }

    CounterFitting convertToCounterFitting(CounterfittingEntity entity) {
        LinkedHashSet<Calibration> calibrations = new LinkedHashSet<>();
        
        entity.getCalibrations()
                .stream()
                .map(calibration -> new Calibration(calibration.getCalibration()))
                .forEach(calibrations::add);
        
        return new CounterFitting(UUID.fromString(entity.getId()),
                entity.getDescription(),
                new Installation(entity.getInstallation()),
                new Manufacturer(entity.getManufacturer()),
                calibrations);
    }
}

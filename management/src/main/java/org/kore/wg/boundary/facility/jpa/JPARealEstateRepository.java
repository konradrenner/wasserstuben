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
import javax.persistence.Query;
import org.kore.wg.control.facility.RealEstateRepository;
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

    @Inject
    EntityManager em;

    @Override
    public Set<RealEstate> findAll() {

        List<RealEstateEntity> resultList = em.createNamedQuery(RealEstateEntity.FIND_ALL, RealEstateEntity.class).getResultList();

        Logger.getLogger("JPARealEstateRepository").info("" + resultList);

        LinkedHashSet<RealEstate> realEstates = new LinkedHashSet<>(resultList.size());
        resultList.stream().map(this::convertToRealEstate).forEach(realEstates::add);

        return realEstates;
    }

    @Override
    public Result find(String search, ResultArea area, Ordering ordering) {
        long totalCountOfRealEstates = getTotalCountOfRealEstates();
        SortedSet<RealEstate> realEstates = new TreeSet<>();

        if (totalCountOfRealEstates == 0) {
            return new Result(realEstates, totalCountOfRealEstates);
        }

        try {
            Long numericSearchValue = Long.valueOf(search);
            findFromAllFields(search, numericSearchValue, area, ordering, realEstates);
        } catch (NumberFormatException e) {
            findFromAlphanumericFields(search, area, ordering);
        }
        return new Result(realEstates, totalCountOfRealEstates);
    }

    Result findFromAlphanumericFields(String search, ResultArea area, Ordering ordering) {
        return null;
    }

    void findFromAllFields(String ssearch, Long lsearch, ResultArea area, Ordering ordering, SortedSet<RealEstate> realEstates) {
        em.createNamedQuery(RealEstateEntity.FIND_BY_ALL_FIELDS, RealEstateEntity.class)
                .setParameter("ssearch", "%" + ssearch + "%")
                .setParameter("lsearch", lsearch)
                .setFirstResult((int) area.start())
                .setMaxResults((int) area.maxCount())
                .getResultStream()
                .map(this::convertToRealEstate)
                .forEach(realEstates::add);
    }

    long getTotalCountOfRealEstates() {
        Query queryTotal = em.createQuery("select count(e.id) from RealEstateEntity e");
        return (long) queryTotal.getSingleResult();
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

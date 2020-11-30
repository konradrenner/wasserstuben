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
package org.kore.wg.boundary.facility.db;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import org.kore.wg.control.facility.RealEstateRepository;
import org.kore.wg.entity.facility.CounterFitting;
import org.kore.wg.entity.facility.RealEstate;
import org.kore.wg.entity.facility.Owner;
import org.kore.wg.entity.facility.Installation;
import org.kore.wg.entity.facility.Manufacturer;
import org.kore.wg.entity.facility.Calibration;
import org.kore.wg.entity.facility.RealEstateId;
import org.kore.wg.entity.facility.Name;

/**
 *
 * @author Konrad Renner
 */
@ApplicationScoped
public class JPARealEstateRepository implements RealEstateRepository{

    @Override
    public Set<RealEstate> findAll() {
        LinkedHashSet<RealEstate> ret = new LinkedHashSet<>();
        
        Owner owner = new Owner(UUID.randomUUID(),new Name("Max", "Mustermann"));
        CounterFitting fitting = new CounterFitting(UUID.randomUUID(),
                "Musterfrau Fitting 2",
                new Installation(LocalDateTime.of(2000, Month.MARCH, 15, 10, 11).toInstant(ZoneOffset.UTC)),
                new Manufacturer("Luigi"),
                Arrays.asList(new Calibration(Instant.now()), new Calibration(LocalDateTime.of(2000, Month.MARCH, 15, 10, 11).toInstant(ZoneOffset.UTC))));

        CounterFitting fitting2 = new CounterFitting(UUID.randomUUID(),
                "Mustermann Fitting 1",
                new Installation(Instant.now()),
                new Manufacturer("Super Mario"),
                Arrays.asList(new Calibration(Instant.now())));
        
        RealEstateId id = new RealEstateId(1, "asbc", 2);
        
        RealEstate realEstate = new RealEstate(id, Arrays.asList(owner), Arrays.asList(fitting, fitting2));
        ret.add(realEstate);
        
        Owner owner2 = new Owner(UUID.randomUUID(),new Name("Mizzi", "Musterfrau"));
        fitting = new CounterFitting(UUID.randomUUID(),
                "Musterfrau Fitting 1",
                new Installation(Instant.now()), 
                new Manufacturer("Super Mario"), 
                Arrays.asList(new Calibration(Instant.now())));

        
        id = new RealEstateId(3, "xyz", 9);
        
        realEstate = new RealEstate(id, Arrays.asList(owner, owner2), Arrays.asList(fitting));
        ret.add(realEstate);
        
        return ret;
    }
    
}

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
package org.kore.wg.control.facility;

import java.util.Set;
import java.util.SortedSet;
import org.kore.wg.entity.facility.RealEstate;

/**
 *
 * @author Konrad Renner
 */
public interface RealEstateRepository {
 
    Set<RealEstate> findAll();

    Result find(String search, ResultArea area, Ordering ordering);

    record ResultArea(long start, long maxCount) {

    }

    record Ordering(OrderingProperty property, OrderingDirection direction) {
    }

    record Result(SortedSet<RealEstate> estates, long totalCount) {

    }

    enum OrderingProperty {
        OWNER, CATASTRALDOWNSHIPNUMBER, ESTATEID, DEPOSITNUMBER;
    }

    enum OrderingDirection {
        ASC, DESC;
    }
}

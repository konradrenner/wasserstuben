/*
 * Copyright (C) 2021 Konrad Renner
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

/**
 *
 * @author Konrad Renner
 */
public interface RealEstateSearchBuilder {

    WithResultArea createWithSearchString(String value);

    interface WithResultArea {

        WithOrderingDirection resultArea(ResultArea value);

        Search build();
    }

    interface WithOrderingDirection {

        WithOrderingProperty orderingDirection(OrderingDirection value);

        Search build();
    }

    interface WithOrderingProperty {

        WithOrderingProperty orderingProperty(OrderingProperty property);

        Search build();
    }

    enum OrderingProperty {
        OWNER, CADASTRALTOWNSHIPNUMBER, ESTATEID, DEPOSITNUMBER;

        public static OrderingProperty evalute(String val) {
            if (val == null || val.isBlank()) {
                return CADASTRALTOWNSHIPNUMBER;
            }
            return OrderingProperty.valueOf(val.toUpperCase());
        }
    }

    enum OrderingDirection {
        ASC, DESC;

        public static OrderingDirection evalute(String val) {
            if (val == null || val.isBlank()) {
                return ASC;
            }
            return OrderingDirection.valueOf(val.toUpperCase());
        }
    }

    record ResultArea(long start, long maxCount) {

    }

    record Search(String query, ResultArea area, OrderingDirection direction, OrderingProperty orderingProperty) {

    }
}

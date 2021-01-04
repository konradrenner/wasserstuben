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
package org.kore.wg.entity.facility;

/**
 *
 * @author Konrad Renner
 */
public record RealEstateId(long cadastralTownshipNumber, String estateId, long depositNumber) implements Comparable<RealEstateId> {

    @Override
    public int compareTo(RealEstateId o) {
        if (this.equals(o)) {
            return 0;
        }

        int ret = (int) (cadastralTownshipNumber - o.cadastralTownshipNumber);
        if (ret == 0) {
            ret = estateId.compareTo(o.estateId);
        }
        if (ret == 0) {
            ret = (int) (depositNumber - o.depositNumber);
        }
        return ret;
    }
}

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
package org.kore.wg.boundary.facility.ui;

import org.kore.wg.entity.facility.RealEstateId;
/**
 *
 * @author Konrad Renner
 */
public class RealEstateIdModel {
    private long cadastralTownshipNumber;
    private String estateId;
    private long depositNumber;
    
    public static final RealEstateIdModel from(RealEstateId id){
        RealEstateIdModel model = new RealEstateIdModel();
        model.cadastralTownshipNumber = id.cadastralTownshipNumber();
        model.estateId = id.estateId();
        model.depositNumber = id.depositNumber();
        return model;
    }

    public long getCadastralTownshipNumber() {
        return cadastralTownshipNumber;
    }

    public void setCadastralTownshipNumber(long cadastralTownshipNumber) {
        this.cadastralTownshipNumber = cadastralTownshipNumber;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public long getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(long depositNumber) {
        this.depositNumber = depositNumber;
    }

    @Override
    public String toString() {
        return "RealEstateIdModel{" + "cadastralTownshipNumber=" + cadastralTownshipNumber + ", estateId=" + estateId + ", depositNumber=" + depositNumber + '}';
    }
}

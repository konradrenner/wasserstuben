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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import javax.json.bind.annotation.JsonbProperty;
import org.kore.wg.entity.facility.CounterFitting;
import org.kore.wg.entity.facility.Owner;
import org.kore.wg.entity.facility.RealEstate;

/**
 *
 * @author Konrad Renner
 */
public class RealEstateModel {

    @JsonbProperty("realestate-id")
    private RealEstateIdModel id;
    private Collection<OwnerModel> owner;
    @JsonbProperty("counter-fittings")
    private Collection<CounterFittingModel> fittings;

    public static final RealEstateModel from(RealEstate estate){
        RealEstateModel model = new RealEstateModel();
        model.id = RealEstateIdModel.from(estate.getId());
        
        Set<CounterFitting> fits = estate.getFittings();
        Set<Owner> owns = estate.getOwner();
        
        model.owner = new ArrayList<>(owns.size());
        owns.stream().map(OwnerModel::from).forEach(model.owner::add);
        
        model.fittings = new ArrayList<>(fits.size());
        fits.stream().map(CounterFittingModel::from).forEach(model.fittings::add);
        
        return model;
    }

    public RealEstateIdModel getId() {
        return id;
    }

    public void setId(RealEstateIdModel id) {
        this.id = id;
    }

    public Collection<OwnerModel> getOwner() {
        return owner;
    }

    public void setOwner(Collection<OwnerModel> owner) {
        this.owner = owner;
    }

    public Collection<CounterFittingModel> getFittings() {
        return fittings;
    }

    public void setFittings(Collection<CounterFittingModel> fittings) {
        this.fittings = fittings;
    }

    
    @Override
    public String toString() {
        return "RealEstateModel{" + "id=" + id + ", owner=" + owner + ", fittings=" + fittings + '}';
    }
}

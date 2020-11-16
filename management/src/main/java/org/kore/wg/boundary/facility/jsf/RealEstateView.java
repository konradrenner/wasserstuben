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
package org.kore.wg.boundary.facility.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.kore.wg.boundary.facility.ui.RealEstateModel;
import org.kore.wg.control.facility.RealEstateRepository;
import org.kore.wg.entity.facility.RealEstate;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Konrad Renner
 */
@Named("realEstateView")
@ViewScoped
public class RealEstateView implements Serializable {

    @Inject
    RealEstateRepository repo;

    private List<RealEstateModel> realEstates;

    private RealEstateModel selectedRealEstate;

    public void init() {
        realEstates = new ArrayList<>();
        Set<RealEstate> all = repo.findAll();
        all.stream().map(RealEstateModel::from).forEach(realEstates::add);
    }

    public List<RealEstateModel> getRealEstates() {
        return realEstates;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Real Estate Selected", ((RealEstateModel) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public RealEstateModel getSelectedRealEstate() {
        return selectedRealEstate;
    }

}

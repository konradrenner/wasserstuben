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

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.UUID;
import javax.json.bind.annotation.JsonbProperty;
import org.kore.wg.entity.facility.Calibration;
import org.kore.wg.entity.facility.CounterFitting;

/**
 *
 * @author Konrad Renner
 */
public class CounterFittingModel {

    @JsonbProperty("counterfitting-id")
    private UUID id;
    private String manufacturer;
    private ZonedDateTime installation;
    private Collection<ZonedDateTime> calibrations;
    
    public static final CounterFittingModel from(CounterFitting fitting){
        CounterFittingModel model = new CounterFittingModel();
        model.id = fitting.getId();
        model.manufacturer = fitting.getManufacturer().name();
        model.installation = fitting.getInstallation().value().atZone(ZoneId.systemDefault());
        
        SortedSet<Calibration> calis = fitting.getCalibrations();
        
        model.calibrations = new ArrayList<>(calis.size());
        calis.stream().map(cali -> cali.timestamp())
                .map(tst -> tst.atZone(ZoneId.systemDefault()))
                .forEach(model.calibrations::add);
        
        return model;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ZonedDateTime getInstallation() {
        return installation;
    }

    public void setInstallation(ZonedDateTime installation) {
        this.installation = installation;
    }

    public Collection<ZonedDateTime> getCalibrations() {
        return calibrations;
    }

    public void setCalibrations(Collection<ZonedDateTime> calibrations) {
        this.calibrations = calibrations;
    }


    @Override
    public String toString() {
        return "CounterFittingModel{" + "id=" + id + ", manufacturer=" + manufacturer + ", installation=" + installation + ", calibrations=" + calibrations + '}';
    }
    
}

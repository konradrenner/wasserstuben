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

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.kore.wg.boundary.jpa.DefaultEntity;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "COUNTERFITTING")
public class CounterfittingEntity extends DefaultEntity implements Serializable {

    private long version;
    private String description;
    private String manufacturer;
    //sever stores in UTC!
    @Column(name = "installation", columnDefinition = "TIMESTAMP")
    private Instant installation;

    @ManyToOne
    @JoinColumn(name = "REALESTATE_ID")
    private RealEstateEntity realEstate;

    @OneToMany(mappedBy = "counterfitting", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CounterfittingCalibrationEntity> calibrations;

    protected CounterfittingEntity() {
        // declared protected
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Instant getInstallation() {
        return installation;
    }

    void setInstallation(Instant installation) {
        this.installation = installation;
    }

    List<CounterfittingCalibrationEntity> getCalibrations() {
        return calibrations;
    }

    public void addCalibration(CounterfittingCalibrationEntity calibration) {
        this.calibrations.add(calibration);
    }


    @Override
    public String toString() {
        return "CounterfittingEntity{" + super.toString() + ", description=" + description + ", manufacturer=" + manufacturer + ", installation=" + installation + ", calibrations=" + calibrations + '}';
    }

}

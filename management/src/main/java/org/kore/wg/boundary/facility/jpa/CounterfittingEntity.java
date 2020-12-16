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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "COUNTERFITTING")
public class CounterfittingEntity implements Serializable {
    @Id
    private String id;
    @Version
    private long version;
    private String description;
    private String manufacturer;
    //sever stores in UTC!
    @Column(name = "installation", columnDefinition = "TIMESTAMP")
    private LocalDateTime installation;

    @ManyToOne
    @JoinColumn(name = "REALESTATE_ID")
    private RealEstateEntity realEstate;

    @OneToMany(mappedBy = "counterfitting", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CounterfittingCalibrationEntity> calibrations;

    protected CounterfittingEntity() {
        // JPA
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDateTime getInstallation() {
        return installation;
    }

    public void setInstallation(LocalDateTime installation) {
        this.installation = installation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CounterfittingEntity other = (CounterfittingEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CounterfittingEntity{" + "id=" + id + ", version=" + version + ", description=" + description + ", manufacturer=" + manufacturer + ", installation=" + installation + ", calibrations=" + calibrations + '}';
    }

}

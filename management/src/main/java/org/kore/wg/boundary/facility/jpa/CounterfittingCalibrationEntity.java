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
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "COUNTERFITTING_CALIBRATION")
@IdClass(CounterfittingCalibrationKey.class)
public class CounterfittingCalibrationEntity implements Serializable, Comparable<CounterfittingCalibrationEntity> {

    @Id
    private Instant calibration;
    @Id
    @ManyToOne
    private CounterfittingEntity counterfitting;
    @Version
    private long version;

    protected CounterfittingCalibrationEntity() {
        // JPA
    }

    public CounterfittingEntity getCounterfitting() {
        return counterfitting;
    }

    void setCounterfitting(CounterfittingEntity counterfitting) {
        this.counterfitting = counterfitting;
    }

    public Instant getCalibration() {
        return calibration;
    }

    void setCalibration(Instant calibration) {
        this.calibration = calibration;
    }

    public long getVersion() {
        return version;
    }

    void setVersion(long version) {
        this.version = version;
    }

    @Override
    public int compareTo(CounterfittingCalibrationEntity o) {
        if (this.equals(o)) {
            return 0;
        }
        int result = o.getCalibration().compareTo(this.getCalibration());
        if (result == 0) {
            result = o.getCounterfitting().getId().compareTo(this.getCounterfitting().getId());
        }
        return result;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.counterfitting);
        hash = 73 * hash + Objects.hashCode(this.calibration);
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
        final CounterfittingCalibrationEntity other = (CounterfittingCalibrationEntity) obj;
        if (!Objects.equals(this.counterfitting, other.counterfitting)) {
            return false;
        }
        if (!Objects.equals(this.calibration, other.calibration)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CounterfittingCalibrationEntity{" + "calibration=" + calibration + ", version=" + version + '}';
    }

}

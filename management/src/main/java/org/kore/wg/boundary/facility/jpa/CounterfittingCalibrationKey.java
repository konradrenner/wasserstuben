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

/**
 *
 * @author Konrad Renner
 */
public class CounterfittingCalibrationKey implements Serializable, Comparable<CounterfittingCalibrationKey> {

    private Instant calibration;
    private String counterfitting;

    public Instant getCalibration() {
        return calibration;
    }

    public void setCalibration(Instant calibration) {
        this.calibration = calibration;
    }

    public String getCounterfitting() {
        return counterfitting;
    }

    public void setCounterfitting(String counterfitting) {
        this.counterfitting = counterfitting;
    }

    @Override
    public int compareTo(CounterfittingCalibrationKey o) {
        if (this.equals(o)) {
            return 0;
        }
        int result = o.getCalibration().compareTo(this.getCalibration());
        if (result == 0) {
            result = o.getCounterfitting().compareTo(this.getCounterfitting());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.calibration);
        hash = 67 * hash + Objects.hashCode(this.counterfitting);
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
        final CounterfittingCalibrationKey other = (CounterfittingCalibrationKey) obj;
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
        return "CounterfittingCalibrationKey{" + "calibration=" + calibration + ", counterfittingId=" + counterfitting + '}';
    }

}

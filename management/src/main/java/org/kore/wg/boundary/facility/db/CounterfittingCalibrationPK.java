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
package org.kore.wg.boundary.facility.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
@Embeddable
public class CounterfittingCalibrationPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "counterfitting_id")
    private Object counterfittingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calibration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calibration;

    public CounterfittingCalibrationPK() {
    }

    public CounterfittingCalibrationPK(Object counterfittingId, Date calibration) {
        this.counterfittingId = counterfittingId;
        this.calibration = calibration;
    }

    public Object getCounterfittingId() {
        return counterfittingId;
    }

    public void setCounterfittingId(Object counterfittingId) {
        this.counterfittingId = counterfittingId;
    }

    public Date getCalibration() {
        return calibration;
    }

    public void setCalibration(Date calibration) {
        this.calibration = calibration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (counterfittingId != null ? counterfittingId.hashCode() : 0);
        hash += (calibration != null ? calibration.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CounterfittingCalibrationPK)) {
            return false;
        }
        CounterfittingCalibrationPK other = (CounterfittingCalibrationPK) object;
        if ((this.counterfittingId == null && other.counterfittingId != null) || (this.counterfittingId != null && !this.counterfittingId.equals(other.counterfittingId))) {
            return false;
        }
        if ((this.calibration == null && other.calibration != null) || (this.calibration != null && !this.calibration.equals(other.calibration))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.wg.boundary.facility.db.CounterfittingCalibrationPK[ counterfittingId=" + counterfittingId + ", calibration=" + calibration + " ]";
    }

}

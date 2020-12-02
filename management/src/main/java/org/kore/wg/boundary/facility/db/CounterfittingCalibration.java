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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "counterfitting_calibration")
@NamedQueries({
    @NamedQuery(name = "CounterfittingCalibration.findAll", query = "SELECT c FROM CounterfittingCalibration c"),
    @NamedQuery(name = "CounterfittingCalibration.findByVersion", query = "SELECT c FROM CounterfittingCalibration c WHERE c.version = :version"),
    @NamedQuery(name = "CounterfittingCalibration.findByCalibration", query = "SELECT c FROM CounterfittingCalibration c WHERE c.counterfittingCalibrationPK.calibration = :calibration")})
public class CounterfittingCalibration implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CounterfittingCalibrationPK counterfittingCalibrationPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private long version;
    @JoinColumn(name = "counterfitting_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Counterfitting counterfitting;

    public CounterfittingCalibration() {
    }

    public CounterfittingCalibration(CounterfittingCalibrationPK counterfittingCalibrationPK) {
        this.counterfittingCalibrationPK = counterfittingCalibrationPK;
    }

    public CounterfittingCalibration(CounterfittingCalibrationPK counterfittingCalibrationPK, long version) {
        this.counterfittingCalibrationPK = counterfittingCalibrationPK;
        this.version = version;
    }

    public CounterfittingCalibration(Object counterfittingId, Date calibration) {
        this.counterfittingCalibrationPK = new CounterfittingCalibrationPK(counterfittingId, calibration);
    }

    public CounterfittingCalibrationPK getCounterfittingCalibrationPK() {
        return counterfittingCalibrationPK;
    }

    public void setCounterfittingCalibrationPK(CounterfittingCalibrationPK counterfittingCalibrationPK) {
        this.counterfittingCalibrationPK = counterfittingCalibrationPK;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Counterfitting getCounterfitting() {
        return counterfitting;
    }

    public void setCounterfitting(Counterfitting counterfitting) {
        this.counterfitting = counterfitting;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (counterfittingCalibrationPK != null ? counterfittingCalibrationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CounterfittingCalibration)) {
            return false;
        }
        CounterfittingCalibration other = (CounterfittingCalibration) object;
        if ((this.counterfittingCalibrationPK == null && other.counterfittingCalibrationPK != null) || (this.counterfittingCalibrationPK != null && !this.counterfittingCalibrationPK.equals(other.counterfittingCalibrationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.wg.boundary.facility.db.CounterfittingCalibration[ counterfittingCalibrationPK=" + counterfittingCalibrationPK + " ]";
    }

}

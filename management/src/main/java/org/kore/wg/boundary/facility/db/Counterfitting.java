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
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "counterfitting")
@NamedQueries({
    @NamedQuery(name = "Counterfitting.findAll", query = "SELECT c FROM Counterfitting c"),
    @NamedQuery(name = "Counterfitting.findByVersion", query = "SELECT c FROM Counterfitting c WHERE c.version = :version"),
    @NamedQuery(name = "Counterfitting.findByCadastraltownship", query = "SELECT c FROM Counterfitting c WHERE c.cadastraltownship = :cadastraltownship"),
    @NamedQuery(name = "Counterfitting.findByEstateid", query = "SELECT c FROM Counterfitting c WHERE c.estateid = :estateid"),
    @NamedQuery(name = "Counterfitting.findByDepostid", query = "SELECT c FROM Counterfitting c WHERE c.depostid = :depostid"),
    @NamedQuery(name = "Counterfitting.findByDescription", query = "SELECT c FROM Counterfitting c WHERE c.description = :description"),
    @NamedQuery(name = "Counterfitting.findByInstallation", query = "SELECT c FROM Counterfitting c WHERE c.installation = :installation"),
    @NamedQuery(name = "Counterfitting.findByManufacturer", query = "SELECT c FROM Counterfitting c WHERE c.manufacturer = :manufacturer")})
public class Counterfitting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private long version;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "id")
    private Object id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cadastraltownship")
    private long cadastraltownship;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "estateid")
    private String estateid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "depostid")
    private long depostid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "installation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date installation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "manufacturer")
    private String manufacturer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "counterfitting")
    private Collection<CounterfittingCalibration> counterfittingCalibrationCollection;

    public Counterfitting() {
    }

    public Counterfitting(Object id) {
        this.id = id;
    }

    public Counterfitting(Object id, long version, long cadastraltownship, String estateid, long depostid, String description, Date installation, String manufacturer) {
        this.id = id;
        this.version = version;
        this.cadastraltownship = cadastraltownship;
        this.estateid = estateid;
        this.depostid = depostid;
        this.description = description;
        this.installation = installation;
        this.manufacturer = manufacturer;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public long getCadastraltownship() {
        return cadastraltownship;
    }

    public void setCadastraltownship(long cadastraltownship) {
        this.cadastraltownship = cadastraltownship;
    }

    public String getEstateid() {
        return estateid;
    }

    public void setEstateid(String estateid) {
        this.estateid = estateid;
    }

    public long getDepostid() {
        return depostid;
    }

    public void setDepostid(long depostid) {
        this.depostid = depostid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getInstallation() {
        return installation;
    }

    public void setInstallation(Date installation) {
        this.installation = installation;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Collection<CounterfittingCalibration> getCounterfittingCalibrationCollection() {
        return counterfittingCalibrationCollection;
    }

    public void setCounterfittingCalibrationCollection(Collection<CounterfittingCalibration> counterfittingCalibrationCollection) {
        this.counterfittingCalibrationCollection = counterfittingCalibrationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Counterfitting)) {
            return false;
        }
        Counterfitting other = (Counterfitting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.wg.boundary.facility.db.Counterfitting[ id=" + id + " ]";
    }

}

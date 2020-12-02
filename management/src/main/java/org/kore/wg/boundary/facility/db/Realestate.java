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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "realestate")
@NamedQueries({
    @NamedQuery(name = "Realestate.findAll", query = "SELECT r FROM Realestate r"),
    @NamedQuery(name = "Realestate.findByVersion", query = "SELECT r FROM Realestate r WHERE r.version = :version"),
    @NamedQuery(name = "Realestate.findByCadastraltownship", query = "SELECT r FROM Realestate r WHERE r.cadastraltownship = :cadastraltownship"),
    @NamedQuery(name = "Realestate.findByEstateid", query = "SELECT r FROM Realestate r WHERE r.estateid = :estateid"),
    @NamedQuery(name = "Realestate.findByDepostid", query = "SELECT r FROM Realestate r WHERE r.depostid = :depostid")})
public class Realestate implements Serializable {

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

    public Realestate() {
    }

    public Realestate(Object id) {
        this.id = id;
    }

    public Realestate(Object id, long version, long cadastraltownship, String estateid, long depostid) {
        this.id = id;
        this.version = version;
        this.cadastraltownship = cadastraltownship;
        this.estateid = estateid;
        this.depostid = depostid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Realestate)) {
            return false;
        }
        Realestate other = (Realestate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.wg.boundary.facility.db.Realestate[ id=" + id + " ]";
    }

}

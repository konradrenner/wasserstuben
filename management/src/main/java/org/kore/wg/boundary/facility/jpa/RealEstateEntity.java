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
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "REALESTATE")
@NamedQuery(name = RealEstateEntity.FIND_ALL, query = "select realestate from RealEstateEntity realestate")
public class RealEstateEntity implements Serializable {

    static final String FIND_ALL = "";

    @Id
    private String id;
    @Version
    private long version;
    private long cadastralTownshipNumber;
    private String estateId;
    private long depositNumber;

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

    public long getCadastralTownshipNumber() {
        return cadastralTownshipNumber;
    }

    public void setCadastralTownshipNumber(long cadastralTownshipNumber) {
        this.cadastralTownshipNumber = cadastralTownshipNumber;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public long getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(long depositNumber) {
        this.depositNumber = depositNumber;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final RealEstateEntity other = (RealEstateEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RealEstateEntity{" + "id=" + id + ", version=" + version + ", cadastralTownshipNumber=" + cadastralTownshipNumber + ", estateId=" + estateId + ", depositNumber=" + depositNumber + '}';
    }

}
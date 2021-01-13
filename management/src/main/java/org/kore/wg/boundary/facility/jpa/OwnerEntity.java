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
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.kore.wg.boundary.jpa.DefaultEntity;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "OWNER")
public class OwnerEntity extends DefaultEntity implements Serializable {

    private String firstname;
    private String lastname;

    @ManyToMany(mappedBy = "owners")
    private List<RealEstateEntity> realEstates;

    protected OwnerEntity() {
        // declared protected
    }

    Collection<RealEstateEntity> getRealEstates() {
        return realEstates;
    }

    public String getFirstname() {
        return firstname;
    }

    void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "OwnerEntity{" + super.toString() + ", firstname=" + firstname + ", lastname=" + lastname + '}';
    }

}

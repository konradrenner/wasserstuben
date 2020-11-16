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
package org.kore.wg.boundary.facility.ui;

import java.util.UUID;
import javax.json.bind.annotation.JsonbProperty;
import org.kore.wg.entity.facility.Owner;

/**
 *
 * @author Konrad Renner
 */
public class OwnerModel {

    @JsonbProperty("owner-id")
    private UUID id;
    private String firstname;
    private String lastname;

    public static final OwnerModel from(Owner owner){
        OwnerModel model = new OwnerModel();
        model.id = owner.id();
        model.firstname = owner.name().firstname();
        model.lastname = owner.name().lastname();
        return model;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Override
    public String toString() {
        return "OwnerModel{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + '}';
    }
    
}

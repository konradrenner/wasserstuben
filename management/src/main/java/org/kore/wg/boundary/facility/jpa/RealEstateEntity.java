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
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.kore.wg.boundary.jpa.DefaultEntity;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "REALESTATE")
public class RealEstateEntity extends DefaultEntity implements Serializable {

    static final String FIND_ALL = "RealEstateEntity.findAll";
    static final String FIND_BY_ALL_FIELDS = "RealEstateEntity.findByAllFields";
    static final String FIND_BY_ALPHANUMERIC_FIELDS = "RealEstateEntity.findByAlphanumericFields";

    private long cadastraltownshipnumber;
    private String estateId;
    private long depositnumber;

    @OneToMany(mappedBy = "realEstate", orphanRemoval = true, fetch = FetchType.LAZY)
    @MapKey(name = "id")
    private Map<String, CounterfittingEntity> counterfittings;

    @ManyToMany
    @JoinTable(
            name = "REALESTATE_OWNER",
            joinColumns = @JoinColumn(name = "REALESTATE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    )
    @MapKey(name = "id")
    private Map<String, OwnerEntity> owners;

    protected RealEstateEntity() {
        // declared protected
    }

    Collection<CounterfittingEntity> getCounterfittings() {
        return counterfittings.values();
    }

    void updateCounterfittings(Collection<CounterfittingEntity> fittings) {
        //TODO: add new fittings, delete those which are not in the collection. Important: in den CounterFittingEntity the RealEstateEntity must also be updated!
    }

    Collection<OwnerEntity> getOwners() {
        return owners.values();
    }

    public long getCadastralTownshipNumber() {
        return cadastraltownshipnumber;
    }

    void setCadastralTownshipNumber(long cadastralTownshipNumber) {
        this.cadastraltownshipnumber = cadastralTownshipNumber;
    }

    public String getEstateId() {
        return estateId;
    }

    void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public long getDepositNumber() {
        return depositnumber;
    }

    void setDepositNumber(long depositNumber) {
        this.depositnumber = depositNumber;
    }

    @Override
    public String toString() {
        return "RealEstateEntity{ " + super.toString() + ", cadastralTownshipNumber=" + cadastraltownshipnumber + ", estateId=" + estateId + ", depositNumber=" + depositnumber + ", counterfittings=" + counterfittings + ", owners=" + owners + '}';
    }

}

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
package org.kore.wg.entity.facility;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Konrad Renner
 */
public class RealEstate {
    private final RealEstateId id;
    private final Map<UUID, Owner> owner;
    private final Map<UUID, CounterFitting> fittings;

    public RealEstate(RealEstateId id, Collection<Owner> owner, Collection<CounterFitting> fittings) {
        this.id = id;
        this.owner = new HashMap<>(owner.size());
        owner.forEach(own -> this.owner.put(own.id(), own));
        this.fittings = new HashMap<>(fittings.size());
        fittings.forEach(fitting -> this.fittings.put(fitting.getId(), fitting));
    }
    
    

    public RealEstateId getId() {
        return id;
    }

    public Set<Owner> getOwner() {
        return new LinkedHashSet<>(owner.values());
    }

    public Set<CounterFitting> getFittings() {
        return new LinkedHashSet<>(fittings.values());
    }
    
    public void changeOwnership(Set<Owner> owner){
        this.owner.clear();
        owner.forEach(own -> this.owner.put(own.id(), own));
    }
    
    public void addOwnership(Owner additionalOwner){
        this.owner.put(additionalOwner.id(), additionalOwner);
    }
    
    public void removeOwnership(Owner additionalOwner){
        this.owner.remove(additionalOwner.id());
    }
    
    public void replaceCounterFitting(UUID oldFitting, CounterFitting newFitting){
        this.fittings.replace(oldFitting, newFitting);
    }

    @Override
    public String toString() {
        return "RealEstate{" + "id=" + id + ", owner=" + owner + ", fitting=" + fittings + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final RealEstate other = (RealEstate) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}

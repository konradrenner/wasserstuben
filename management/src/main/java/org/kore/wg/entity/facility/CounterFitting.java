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
import java.util.Collections;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

/**
 *
 * @author Konrad Renner
 */
public class CounterFitting {
    private final UUID id;
    private final String description;
    private final Installation installation;
    private final Manufacturer manufacturer;
    private final SortedSet<Calibration> calibrations;

    public CounterFitting(UUID id, String description, Installation installation, Manufacturer manufacturer) {
        this(id, description, installation, manufacturer, new TreeSet<>());
    }

    public CounterFitting(UUID id, String description, Installation installation, Manufacturer manufacturer, Collection<Calibration> calibrations) {
        this.id = id;
        this.description = description;
        this.installation = installation;
        this.manufacturer = manufacturer;
        this.calibrations = new TreeSet<>(calibrations);
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public Installation getInstallation() {
        return installation;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public SortedSet<Calibration> getCalibrations() {
        return Collections.unmodifiableSortedSet(calibrations);
    }
    
    public void addCalibration(Calibration calibration){
        calibrations.add(calibration);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final CounterFitting other = (CounterFitting) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CounterFitting{" + "id=" + id + ", description=" + description + ", installation=" + installation + ", manufacturer=" + manufacturer + ", calibrations=" + calibrations + '}';
    }

    
}

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
package org.kore.wg.boundary.jpa;

import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 *
 * @author Konrad Renner
 */
@MappedSuperclass
public class DefaultEntity {
    @Id
    private String id;
    @Version
    private long version;

    protected DefaultEntity() {
        // JPA
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    protected void setVersion(long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + (int) (this.version ^ (this.version >>> 32));
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
        if (obj instanceof DefaultEntity) {
            final DefaultEntity other = (DefaultEntity) obj;
            if (this.version != other.version) {
                return false;
            }
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "BaseEntity{" + "id=" + id + ", version=" + version + '}';
    }

}

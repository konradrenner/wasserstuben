/*
 * Copyright (C) 2021 Konrad Renner
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

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import org.kore.wg.control.facility.RealEstateSearchBuilder;

/**
 *
 * @author Konrad Renner
 */
@RequestScoped
public class JPARealEstateSearchBuilder implements RealEstateSearchBuilder, RealEstateSearchBuilder.WithOrderingDirection, RealEstateSearchBuilder.WithOrderingProperty, RealEstateSearchBuilder.WithResultArea {

    private static final Logger LOG = Logger.getLogger(JPARealEstateSearchBuilder.class.getName());

    private StringBuilder query = new StringBuilder("select realestate from RealEstateEntity realestate join realestate.owners owner");
    private OrderingDirection direction = OrderingDirection.ASC;
    private OrderingProperty property = OrderingProperty.OWNER;
    private ResultArea area = new ResultArea(0, 100);
    private String search = "";

    @Override
    public WithResultArea createWithSearchString(String value) {
        this.search = value;
        return this;
    }

    @Override
    public WithOrderingProperty orderingDirection(OrderingDirection value) {
        this.direction = value;
        return this;
    }

    @Override
    public Search build() {
        addWhereClauses();
        addOrderBy();
        return new Search(query.toString(), area, direction, property);
    }

    void addWhereClauses() {
        if (search == null || search.isBlank()) {
            return;
        }

        String lowerCase = search.toLowerCase();
        query.append(" where lower(realestate.estateId) like '%" + lowerCase + "%'");
        query.append(" or lower(owner.lastname) like '%" + lowerCase + "%'");
        query.append(" or lower(owner.firstname) like '%" + lowerCase + "%'");

        try {
            long numeric = Long.valueOf(search);
            query.append(" or realestate.cadastraltownshipnumber = " + numeric);
            query.append(" or realestate.depositnumber = " + numeric);
        } catch (NumberFormatException e) {
            LOG.info("not adding numeric fields to search because of alphanumeric search");
        }
    }
    
    void addOrderBy() {
        query.append(" order by ");
        if (property == OrderingProperty.OWNER) {
            query.append("owners.lastname, owners.firstname ");
        } else {
            query.append("realestate.")
                    .append(property.name().toLowerCase());
        }
        query.append(" ")
                .append(direction.name());

    }

    @Override
    public WithOrderingProperty orderingProperty(OrderingProperty value) {
        this.property = value;
        return this;
    }

    @Override
    public WithOrderingDirection resultArea(ResultArea value) {
        this.area = value;
        return this;
    }

}

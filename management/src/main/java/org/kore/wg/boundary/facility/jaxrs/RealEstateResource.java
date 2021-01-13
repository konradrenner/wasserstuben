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
package org.kore.wg.boundary.facility.jaxrs;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.kore.wg.control.facility.RealEstateRepository;
import org.kore.wg.entity.facility.RealEstate;
import org.kore.wg.entity.facility.RealEstateId;
import org.kore.wg.control.facility.RealEstateSearchBuilder;

/**
 *
 * @author Konrad Renner
 */
@Path("/realestates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RealEstateResource {

    private static final Logger LOG = Logger.getLogger(RealEstateResource.class.getName());
    
    @Inject
    RealEstateRepository repo;

    @Inject
    RealEstateSearchBuilder searchBuilder;

    public RealEstateResource() {
    }

    RealEstateResource(RealEstateRepository repo, RealEstateSearchBuilder searchBuilder) {
        //Testconstructor
        this.repo = repo;
        this.searchBuilder = searchBuilder;
    }


    @GET
    public Response getRealEstates(@QueryParam("search") @DefaultValue("") String search,
            @QueryParam("limit") @DefaultValue("100") long limit,
            @QueryParam("firstindex") @DefaultValue("0") long page,
            @QueryParam("sortproperty") @DefaultValue("CADASTRALTOWNSHIPNUMBER") String sort,
            @QueryParam("order") @DefaultValue("ASC") String order) {
        // TODO: https://opensource.zalando.com/restful-api-guidelines/#json-guidelines
        
        LOG.info("search:" + search);
        LOG.info("limits:" + limit);
        LOG.info("page:" + page);
        LOG.info("sort:" + sort);
        LOG.info("order:" + order);

        RealEstateSearchBuilder.OrderingDirection orderingDirection = RealEstateSearchBuilder.OrderingDirection.evalute(order);
        RealEstateSearchBuilder.OrderingProperty orderingProperty = RealEstateSearchBuilder.OrderingProperty.evalute(sort);
        long maxCount = page + limit;
        RealEstateSearchBuilder.ResultArea area = new RealEstateSearchBuilder.ResultArea(page, maxCount);

        RealEstateSearchBuilder.Search estateSearch = searchBuilder.createWithSearchString(search)
                .resultArea(area)
                .orderingDirection(orderingDirection)
                .orderingProperty(orderingProperty)
                .orderingProperty(orderingProperty)
                .build();

        RealEstateRepository.Result result = repo.find(estateSearch);
        SortedSet<RealEstate> estates = result.estates();
        
        if (estates.isEmpty()) {
            return Response.noContent().build();
        }
        RealEstateListModel estateModels = new RealEstateListModel();
        estateModels.realestates = new ArrayList<>(estates.size());
        estateModels.totalNumber = result.totalCount();
        estates.stream().map(RealEstateModel::from).forEach(estateModels.realestates::add);

        return Response.ok(estateModels).build();
    }

    @GET
    @Path("/{realestateid}")
    public Response getRealEstate(@PathParam("realestateid") String realestateid) {
        String[] splitted = realestateid.split("-");

        RealEstateId id = new RealEstateId(Long.valueOf(splitted[0]), splitted[1], Long.valueOf(splitted[2]));

        Set<RealEstate> allEstates = repo.findAll();
        Optional<RealEstate> real = allEstates.stream().filter(estate -> estate.getId().equals(id)).findFirst();

        return real.map(RealEstateModel::from)
                .map(model -> Response.ok(model).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}

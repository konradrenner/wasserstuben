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
package org.kore.wg.boundary.facility.rest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.kore.wg.control.facility.RealEstateRepository;
import org.kore.wg.entity.facility.RealEstate;
import org.kore.wg.entity.facility.RealEstateId;

/**
 *
 * @author Konrad Renner
 */
@Path("/realestates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RealEstateResource {
    
    @Inject
    RealEstateRepository repo;

    @GET
    public Response getRealEstates(@PathParam("search") String search, @PathParam("limit") long limit, @PathParam("page") long page, @PathParam("sort") String sort, @PathParam("order") String order) {
        // TODO: https://opensource.zalando.com/restful-api-guidelines/#json-guidelines
        Set<RealEstate> allEstates = repo.findAll();
        
        if(allEstates.isEmpty()){
            return Response.noContent().build();
        }

        RealEstateListModel estateModels = new RealEstateListModel();
        estateModels.realestates = new ArrayList<>(allEstates.size());
        estateModels.totalNumber = 500;
        allEstates.stream().map(RealEstateModel::from).forEach(estateModels.realestates::add);

        return Response.ok(estateModels).build();
    }

    @GET
    @Path("/{realestateid}")
    public Response getRealEstate(@PathParam("realestateid") String realestateid) {
        String[] splitted = realestateid.split("-");

        RealEstateId id = new RealEstateId(Long.valueOf(splitted[0]), splitted[1], Long.valueOf(splitted[2]));

        Set<RealEstate> allEstates = repo.findAll();
        Optional<RealEstate> real = allEstates.stream().filter(estate -> estate.getId().equals(id)).findFirst();

        if (real.isPresent()) {
            return Response.ok(real.map(RealEstateModel::from).get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

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

import org.kore.wg.boundary.facility.ui.RealEstateModel;
import java.util.ArrayList;
import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.kore.wg.control.facility.RealEstateRepository;
import org.kore.wg.entity.facility.RealEstate;

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
    public Response getRealEstates(){
        
        Set<RealEstate> allEstates = repo.findAll();
        
        if(allEstates.isEmpty()){
            return Response.noContent().build();
        }

        ArrayList<RealEstateModel> estateModels = new ArrayList<>(allEstates.size());
        allEstates.stream().map(RealEstateModel::from).forEach(estateModels::add);

        return Response.ok(estateModels).build();
    }
}

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
package org.kore.wg.boundary.facility.jaxrs;

import java.util.Collections;
import java.util.TreeSet;
import javax.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kore.wg.control.facility.RealEstateRepository;
import org.kore.wg.control.facility.RealEstateSearchBuilder;
import org.kore.wg.entity.facility.RealEstate;
import org.kore.wg.entity.facility.RealEstateId;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Konrad Renner
 */
@ExtendWith(MockitoExtension.class)
public class RealEstateResourceTest {

    private static final String SEARCH = "someTest";

    @Mock
    RealEstateRepository repo;

    @Mock
    RealEstateSearchBuilder searchBuilder;

    @Mock
    RealEstateSearchBuilder.WithResultArea builderStep;

    @Mock
    RealEstateSearchBuilder.WithOrderingDirection directionStep;

    @Mock
    RealEstateSearchBuilder.WithOrderingProperty orderingStep;

    private RealEstateResource underTest;

    @BeforeEach
    public void setUp() {
        RealEstateSearchBuilder.Search buildedSearch = new RealEstateSearchBuilder.Search(SEARCH, new RealEstateSearchBuilder.ResultArea(0, 100), RealEstateSearchBuilder.OrderingDirection.ASC, RealEstateSearchBuilder.OrderingProperty.CADASTRALTOWNSHIPNUMBER);

        when(searchBuilder.createWithSearchString(SEARCH)).thenReturn(builderStep);
        when(builderStep.resultArea(new RealEstateSearchBuilder.ResultArea(0, 100))).thenReturn(directionStep);
        when(directionStep.orderingDirection(RealEstateSearchBuilder.OrderingDirection.ASC)).thenReturn(orderingStep);
        when(orderingStep.orderingProperty(RealEstateSearchBuilder.OrderingProperty.CADASTRALTOWNSHIPNUMBER)).thenReturn(orderingStep);
        when(orderingStep.build()).thenReturn(buildedSearch);

        underTest = new RealEstateResource(repo, searchBuilder);
    }

    @Test
    public void noContent() {
        when(repo.find(orderingStep.build())).thenReturn(new RealEstateRepository.Result(Collections.emptySortedSet(), 0));

        assertTrue(underTest.getRealEstates(SEARCH, 100, 0, null, "ASC").getStatusInfo() == Response.Status.NO_CONTENT);
    }

    @Test
    public void dataFound() {
        RealEstate estate = new RealEstate(new RealEstateId(0, "abc", 0), Collections.emptyList(), Collections.emptyList());
        TreeSet<RealEstate> estates = new TreeSet<>();
        estates.add(estate);

        when(repo.find(orderingStep.build())).thenReturn(new RealEstateRepository.Result(estates, 1));

        Response response = underTest.getRealEstates(SEARCH, 100, 0, null, "ASC");
        assertTrue(response.getStatusInfo() == Response.Status.OK);
        assertTrue(response.hasEntity());
    }
}

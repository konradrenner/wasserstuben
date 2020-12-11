package org.kore.wg.boundary.facility;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RealEstateResourceIT {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/api/v1/realestates")
                .then()
                .statusCode(200);
    }

}
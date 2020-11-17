package org.kore.wg.boundary.facility;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TenantResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/api/v1/tenant")
                .then()
                .statusCode(200);
    }

}
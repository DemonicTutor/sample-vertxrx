package org.demonworld.sample.vertxrx;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class SwaggerSVT {

  @Test
  void shouldServeSwaggerYaml() {
    // GIVEN
    // EXPECTATIONS
    // WHEN
    given().when().get("http://localhost:8080/swagger-ui/swagger.yaml").then().assertThat().statusCode(200);
    // THEN
    // VERIFY
  }

  @Test
  void shouldServeSwaggerUi() {
    // GIVEN
    // EXPECTATIONS
    // WHEN
    given().when().get("http://localhost:8080/swagger-ui/").then().assertThat().statusCode(200);
    // THEN
    // VERIFY
  }
}

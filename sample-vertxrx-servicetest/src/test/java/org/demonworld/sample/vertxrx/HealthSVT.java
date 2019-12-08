package org.demonworld.sample.vertxrx;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class HealthSVT {

  @Test
  void shouldHead() {
    // GIVEN
    // EXPECTATIONS
    // WHEN
    given().when().head("http://localhost:8080/health").then().assertThat().statusCode(200);
    // THEN
    // VERIFY
  }

  @Test
  void shouldGet() {
    // GIVEN
    // EXPECTATIONS
    // WHEN
    given().when().get("http://localhost:8080/health").then().assertThat().statusCode(200);
    // THEN
    // VERIFY
  }
}

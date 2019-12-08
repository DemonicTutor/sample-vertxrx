package org.demonworld.sample.vertxrx;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class HelloWorldSVT {

  @Test
  void shouldWork() {
    // GIVEN
    // EXPECTATIONS
    // WHEN
    given().when().get("http://localhost:8080/helloworld").then().assertThat().statusCode(200).body(equalTo("Hello from Vert.x!"));
    // THEN
    // VERIFY
  }
}

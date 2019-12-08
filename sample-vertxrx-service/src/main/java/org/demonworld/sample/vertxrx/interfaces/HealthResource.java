package org.demonworld.sample.vertxrx.interfaces;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.ErrorHandler;

public class HealthResource {

  public void router(final Router router) {
    final Route health = router.route("/health");

    health.handler(CorsHandler.create("*").allowedMethod(HttpMethod.HEAD).allowedMethod(HttpMethod.GET));
    health.failureHandler(ErrorHandler.create(true));

    router.get().handler(this::health);
    router.head().handler(this::health);
  }

  private void health(final RoutingContext routingContext) {
    routingContext.response().setStatusCode(HttpResponseStatus.OK.code()).end();
  }
}

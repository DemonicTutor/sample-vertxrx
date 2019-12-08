package org.demonworld.sample.vertxrx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import org.demonworld.sample.vertxrx.interfaces.HealthResource;

public class MainVerticle extends AbstractVerticle {

  private static final int PORT = 8080;
  private static final String HOST = "0.0.0.0";
  private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

  private final HealthResource healthResource = new HealthResource();

  private HttpServer server;

  @Override
  public void start(final Promise<Void> promise) {
    server = vertx.createHttpServer(serverOptions()) //
      .requestHandler(router()) //
      .listen(server -> {
        if (server.succeeded()) {
          promise.complete();
          LOGGER.info("HTTP server started on port 8080");
        } else {
          promise.fail(server.cause());
        }
      });
  }

  @Override
  public void stop(final Promise<Void> promise) {
    if (null == server) {
      promise.complete();
    } else {
      server.close(h -> promise.complete());
    }
  }

  private HttpServerOptions serverOptions() {
    return new HttpServerOptions().setHost(HOST).setPort(PORT);
  }

  private Router router() {
    final Router router = Router.router(vertx);

    router.route("/swagger-ui/*") //
      .handler(StaticHandler.create("swagger-ui") //
        .setIndexPage("index.html") //
        .setCachingEnabled(false));

    router.route("/helloworld") //
      .handler(req -> {
        req.response()
          .putHeader("content-type", "text/plain")
          .end("Hello from Vert.x!");
      });

    healthResource.router(router);

    return router;
  }
}

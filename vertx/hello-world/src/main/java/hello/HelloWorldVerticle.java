package hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author claudioed on 24/06/17. Project vertx
 */
public class HelloWorldVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    Router router = Router.router(this.vertx);
    router.route().handler(BodyHandler.create());
    router.get("/greetings").handler( handler -> {
      final String name = handler.request().getParam("name");
      handler.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html");
      handler.response().setStatusCode(200).end("Hello" + name);

    });
    this.vertx.createHttpServer().requestHandler(router::accept).listen(4004);
  }

}

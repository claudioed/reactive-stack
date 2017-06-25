package bots.verticle;

import bots.FacebookChallengeData;
import bots.infra.module.FacebookBotData;
import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
public class FacebookWebhookVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(FacebookWebhookVerticle.class);

  private final FacebookBotData conf;

  @Inject
  public FacebookWebhookVerticle(FacebookBotData conf) {
    this.conf = conf;
  }

  @Override
  public void start() throws Exception {
    Router router = Router.router(this.vertx);
    router.route().handler(BodyHandler.create());
    router.get("/webhook").handler(rc -> {
      LOGGER.info("request to verify token && challenge");
      final String modeRequestParam = rc.request().getParam("hub.mode");
      final String tokenRequestParam = rc.request().getParam("hub.verify_token");
      final String challengeRequestParam = rc.request().getParam("hub.challenge");
      final FacebookChallengeData challengeData = FacebookChallengeData.builder()
          .mode(modeRequestParam).token(tokenRequestParam).challenge(challengeRequestParam).build();
      if (conf.getVerifyToken().equals(challengeData.getToken())) {
        LOGGER.info("token match...continue request");
        rc.response().setStatusCode(200).end(challengeData.getChallenge());
      } else {
        LOGGER.error("token doesn't match...stop request...FORBIDDEN");
        rc.response().setStatusCode(403).end("Forbidden");
      }
    });

    router.post("/webhook").handler(rc -> {
      LOGGER.info("receive facebook message");
      final JsonObject messages = rc.getBodyAsJson();

    });

    this.vertx.createHttpServer().requestHandler(router::accept).listen(8000);

  }

}

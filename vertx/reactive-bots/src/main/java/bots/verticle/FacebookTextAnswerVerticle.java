package bots.verticle;

import bots.infra.module.FacebookBotData;
import bots.model.FacebookMessageEntry;
import com.google.gson.Gson;
import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
public class FacebookTextAnswerVerticle extends AbstractVerticle {

  private final FacebookBotData conf;

  private final Gson gson;

  @Inject
  public FacebookTextAnswerVerticle(FacebookBotData conf,Gson gson) {
    this.conf = conf;
    this.gson = gson;
  }

  @Override
  public void start() throws Exception {
    final HttpClient vertxHttpClient = vertx.createHttpClient();
    vertx.eventBus().consumer("facebook-text-eb",handler ->{
      final FacebookMessageEntry facebookMessageEntry = gson
          .fromJson(handler.body().toString(), FacebookMessageEntry.class);



      vertxHttpClient.get(conf.getMessageUrl());

    });

  }

}

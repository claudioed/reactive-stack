package bots.verticle;

import bots.infra.module.ApiAIData;
import bots.infra.module.FacebookBotData;
import bots.model.FacebookMessageData;
import bots.model.FacebookTextMessage;
import bots.model.FacebookTextMessage.Message;
import bots.model.FacebookTextMessage.Recipient;
import com.google.gson.Gson;
import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpHeaders;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
public class FacebookTextAnswerVerticle extends AbstractVerticle {

  private final FacebookBotData conf;

  private final Gson gson;

  private final ApiAIData apiAIData;

  @Inject
  public FacebookTextAnswerVerticle(FacebookBotData conf,Gson gson,ApiAIData apiAIData) {
    this.conf = conf;
    this.gson = gson;
    this.apiAIData = apiAIData;
  }

  @Override
  public void start() throws Exception {
    final HttpClient vertxHttpClient = vertx.createHttpClient();
    vertx.eventBus().consumer("facebook-text-eb",handler ->{
      final FacebookMessageData facebookMessageData = gson
          .fromJson(handler.body().toString(), FacebookMessageData.class);
      final String messageContent = facebookMessageData.getMessage().getString("text");



      vertxHttpClient.post(apiAIData.getUrl()).handler(apiAiResponse -> {

        final Message text = Message.builder().text("").build();
        final Recipient recipient = Recipient.builder().id("").build();
        final FacebookTextMessage message = FacebookTextMessage.builder().recipient(recipient).message(text).build();

        vertxHttpClient.post(conf.getMessageUrl()+ "?access_token=" + conf.getProfileToken()).handler(facebookResponse ->{


        }).write(this.gson.toJson(message)).end();



      }).putHeader(HttpHeaders.AUTHORIZATION,"Bearer " + this.apiAIData.getToken()).write("api argument").end();


    });

  }

}

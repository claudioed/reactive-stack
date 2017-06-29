package bots.verticle;

import bots.infra.module.ApiAIData;
import bots.infra.module.FacebookBotData;
import bots.model.ApiAIQuery;
import bots.model.FacebookMessageData;
import bots.model.FacebookTextMessage;
import bots.model.FacebookTextMessage.Message;
import bots.model.FacebookTextMessage.Recipient;
import com.google.gson.Gson;
import com.google.inject.Inject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.client.WebClient;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
public class FacebookTextAnswerVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(FacebookTextAnswerVerticle.class);

  private final FacebookBotData conf;

  private final Gson gson;

  private final ApiAIData apiAIData;

  @Inject
  public FacebookTextAnswerVerticle(FacebookBotData conf, Gson gson, ApiAIData apiAIData) {
    this.conf = conf;
    this.gson = gson;
    this.apiAIData = apiAIData;
  }

  @Override
  public void start() throws Exception {

    vertx.eventBus().consumer("facebook-text-eb", handler -> {

      LOGGER.info("Recebendo mensagem no BUS..." + handler.body().toString());
      final FacebookMessageData facebookMessageData = gson
          .fromJson(handler.body().toString(), FacebookMessageData.class);
      LOGGER.info("Mensagem convertida com sucesso!!!");

      final JsonObject messageNode = Json
          .decodeValue(facebookMessageData.getMessage(), io.vertx.core.json.JsonObject.class);

      LOGGER.info("Conteudo da mensagem => " + messageNode.toString() );

      final String messageContent = messageNode.getString("text");

      LOGGER.info("Texto da mensagem => " + messageNode.toString() );

      final ApiAIQuery aiQuery = ApiAIQuery.builder().query(messageContent)
          .sessionId(this.apiAIData.getSessionId()).build();

      WebClient client = WebClient.create(vertx);

      client
          .post(apiAIData.getUrl())
          .putHeader("Authorization", "Bearer " + this.apiAIData.getToken())
          .rxSendJson(this.gson.toJson(aiQuery)).subscribe(apiAiResponse -> {

        LOGGER.info(String.format("Recebendo mensagem status %s do API AI %s", apiAiResponse.statusCode(),
                apiAiResponse.body()));

        final Message text = Message.builder().text("").build();
        final Recipient recipient = Recipient.builder().id("").build();
        final FacebookTextMessage message = FacebookTextMessage.builder().recipient(recipient)
            .message(text).build();

        client
            .post(conf.getMessageUrl() + "?access_token=" + conf.getProfileToken())
            .rxSendJson(this.gson.toJson(message)).subscribe(apiFacebookResponse -> {

          LOGGER.info(String.format("Recebendo status %s do FACEBOOK %s", apiFacebookResponse.statusCode(),
              apiFacebookResponse.body()));

        });


      });
    });

  }

}

package bots.infra.module;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
public class ReactiveBotsModule extends AbstractModule {

  @Override
  protected void configure() {
  }

  @Provides
  @Singleton
  public FacebookBotData conf() {
    return FacebookBotData.builder().appSecret(System.getenv("FACEBOOK_APP_SECRET"))
        .verifyToken(System.getenv("FACEBOOK_VERIFY_TOKEN")).messageUrl("FACEBOOK_MESSAGE_URL").profileToken("FACEBOOK_PROFILE_TOKEN").build();
  }

  @Provides
  @Singleton
  public ApiAIData apiConf() {
    return ApiAIData.builder().token(System.getenv("API_AI_TOKEN")).url(System.getenv("API_AI_URL")).sessionId(System.getenv("API_AI_SESSION_ID"))
        .build();
  }

  @Provides
  @Singleton
  public Gson gson() {
    return new Gson();
  }

}

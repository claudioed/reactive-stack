package bots.infra.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
@Data
@AllArgsConstructor
public class FacebookBotData {

  String appSecret;

  String verifyToken;

  String messageUrl;

  String profileToken;

  @Builder
  public static FacebookBotData create(@NonNull String appSecret,@NonNull String verifyToken,@NonNull String messageUrl,@NonNull String profileToken){
    return new FacebookBotData(appSecret,verifyToken,messageUrl,profileToken);
  }

}

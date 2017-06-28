package bots.infra.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author claudioed on 26/06/17. Project vertx
 */
@Data
@AllArgsConstructor
public class ApiAIData {

  String url;

  String token;

  String sessionId;

  @Builder
  public static ApiAIData create(@NonNull String url,@NonNull String token,@NonNull String sessionId){
    return new ApiAIData(url,token,sessionId);
  }

}

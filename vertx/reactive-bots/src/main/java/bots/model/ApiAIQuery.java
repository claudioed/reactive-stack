package bots.model;

import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * @author claudioed on 27/06/17. Project vertx
 */
@Value
public class ApiAIQuery {

  final String timezone = "America/Buenos_Aires";

  final String lang = "pt_BR";

  List<String> query;

  String sessionId;

  @Builder
  public static ApiAIQuery createSimpleQuery(@NonNull String query,@NonNull String sessionId){
    return new ApiAIQuery(Collections.singletonList(query),sessionId);
  }

}

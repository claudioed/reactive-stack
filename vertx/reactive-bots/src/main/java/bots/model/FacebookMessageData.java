package bots.model;

import io.vertx.core.json.JsonObject;
import lombok.Data;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
@Data
public class FacebookMessageData {

  Sender sender;

  Recipient recipient;

  Long timestamp;

  JsonObject message;

  @Data
  public static class Sender{

    String id;

  }

  @Data
  public static class Recipient{

    String id;

  }

}

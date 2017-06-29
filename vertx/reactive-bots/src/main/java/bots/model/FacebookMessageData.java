package bots.model;

import lombok.Data;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
@Data
public class FacebookMessageData {

  Sender sender;

  Recipient recipient;

  Long timestamp;

  String message;

  @Data
  public static class Sender{

    String id;

  }

  @Data
  public static class Recipient{

    String id;

  }

}

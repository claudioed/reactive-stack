package bots.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * @author claudioed on 26/06/17. Project vertx
 */
@Value
public class FacebookTextMessage {

  Recipient recipient;

  Message message;

  @Builder
  public static FacebookTextMessage newMessage(@NonNull Recipient recipient,@NonNull Message message){
    return new FacebookTextMessage(recipient,message);
  }

  @Value
  @Builder
  public static class Recipient{

    String id;

  }

  @Value
  @Builder
  public static class Message{

    String text;

  }

}

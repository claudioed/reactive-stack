package bots;

import lombok.Builder;
import lombok.Value;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
@Value
@Builder
public class FacebookChallengeData {

  String mode;

  String token;

  String challenge;

}

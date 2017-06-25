package bots.model;

import java.util.List;
import lombok.Data;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
@Data
public class FacebookMessageEntry {

  String id;

  Long time;

  List<FacebookMessageData> messaging;

}

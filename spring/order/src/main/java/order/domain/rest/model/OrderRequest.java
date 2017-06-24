package order.domain.rest.model;

import java.util.List;
import lombok.Data;

/**
 * @author claudioed on 24/06/17. Project spring
 */
@Data
public class OrderRequest {

  String customer;

  List<String> productIds;

}

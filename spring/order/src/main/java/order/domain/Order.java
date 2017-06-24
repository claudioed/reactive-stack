package order.domain;

import com.sun.tools.corba.se.idl.constExpr.Or;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author claudioed on 24/06/17. Project spring
 */
@Data
@NoArgsConstructor
@Table(value = "orders")
public class Order {

  @PrimaryKey("order_number")
  String order;

  String customer;

  List<Product> items = new ArrayList<>();

  public Order(String order, String customer) {
    this.order = order;
    this.customer = customer;
  }

  public static Order newOrder(@NonNull String order, @NonNull String customer) {
    return new Order(order, customer);
  }

  public Order addItem(final Product item) {
    this.items.add(item);
    return this;
  }

}

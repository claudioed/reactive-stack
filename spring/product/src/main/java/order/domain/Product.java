package order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author claudioed on 24/06/17. Project spring
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "product")
public class Product {

  @PrimaryKey
  String sku;

  String name;

  String description;

  @Builder
  public static Product newProduct(@NonNull String sku,@NonNull String name,@NonNull String description){
    return new Product(sku,name,description);
  }

}

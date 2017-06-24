package order.domain.service;

import lombok.NonNull;
import order.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author claudioed on 24/06/17. Project spring
 */
@Service
public class ProductClient {

  private final WebClient webClient;

  @Value("${product.url}")
  private String productHost;

  @Value("${product.endpoints.by_id}")
  private String productById;

  public ProductClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Product> byId(@NonNull String id) {
    return this.webClient.get().uri(this.productHost + this.productById + id)
        .accept(MediaType.APPLICATION_JSON).exchange().log()
        .flatMap(response -> response.bodyToMono(Product.class));
  }


}

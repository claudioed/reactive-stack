package order.domain.service;

import java.util.UUID;
import order.domain.Order;
import order.domain.Product;
import order.domain.repository.OrderRepository;
import order.domain.rest.model.OrderRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author claudioed on 24/06/17. Project spring
 */
@Service
public class OrderService {

  private final OrderRepository orderRepository;

  private final ProductClient productClient;

  public OrderService(OrderRepository orderRepository, ProductClient productClient) {
    this.orderRepository = orderRepository;
    this.productClient = productClient;
  }

  public Mono<Order> save(final OrderRequest order) {
    final Order newOrder = Order.newOrder(UUID.randomUUID().toString(), order.getCustomer());
    return loadProducts(Flux.fromIterable(order.getProductIds())).map(newOrder::addItem).then(
        this.orderRepository
            .save(newOrder));
  }

  public Mono<Order> loadById(final String id) {
    return this.orderRepository.findById(id);
  }

  public Flux<Order> loadAll() {
    return this.orderRepository.findAll();
  }

  private Flux<Product> loadProducts(Flux<String> ids) {
    return ids.flatMap(this.productClient::byId);
  }

}

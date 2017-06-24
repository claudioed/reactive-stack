package order.domain.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import order.domain.Order;
import order.domain.rest.model.OrderRequest;
import order.domain.service.OrderService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author claudioed on 24/06/17. Project spring
 */
@RestController
@RequestMapping("/api/order")
public class OrderResource {

  private final OrderService orderService;

  public OrderResource(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<Order>> newOne(@RequestBody OrderRequest orderRequest){
    return this.orderService.save(orderRequest).map(data -> ResponseEntity.status(201).body(data));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Order>> loadById(@PathVariable("id") String id){
    return this.orderService.loadById(id).map(data -> ResponseEntity.status(200).body(data));
  }

  @GetMapping
  public Flux<ResponseEntity<Order>> loadAll(){
    return this.orderService.loadAll().map(data -> ResponseEntity.status(200).body(data));
  }

}

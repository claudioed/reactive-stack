package order.domain.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import order.domain.Product;
import order.domain.rest.model.ProductRequest;
import order.domain.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author claudioed on 24/06/17. Project spring
 */
@RestController
@RequestMapping("/api/product")
public class ProductResource {

  private final ProductService productService;

  public ProductResource(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<Product>> newOne(@RequestBody ProductRequest productRequest){
    return this.productService.save(productRequest).map(data -> ResponseEntity.status(201).body(data));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Product>> loadById(@PathVariable("id") String id){
    return this.productService.loadById(id).map(data -> ResponseEntity.status(200).body(data));
  }

  @GetMapping
  public Flux<ResponseEntity<Product>> loadAll(){
    return this.productService.loadAll().map(data -> ResponseEntity.status(200).body(data));
  }

}

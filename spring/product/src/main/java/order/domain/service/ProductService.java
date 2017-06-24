package order.domain.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import order.domain.Product;
import order.domain.repository.ProductRepository;
import order.domain.rest.model.ProductRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author claudioed on 24/06/17. Project spring
 */
@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Mono<Product> save(final ProductRequest product){
    final Product newProduct = Product.builder().sku(UUID.randomUUID().toString())
        .name(product.getName()).description(product.getDescription()).build();
    return this.productRepository.save(newProduct);
  }

  public Mono<Product> loadById(final String id){
    return this.productRepository.findById(id);
  }

  public Flux<Product> loadByIds(List<String> ids){
    return this.productRepository.findAllById(ids);
  }

  public Flux<Product> loadAll(){
    return this.productRepository.findAll();
  }

}

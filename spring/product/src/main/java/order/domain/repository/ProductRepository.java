package order.domain.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import order.domain.Product;

/**
 * @author claudioed on 24/06/17. Project spring
 */
public interface ProductRepository extends ReactiveCassandraRepository<Product,String>{}

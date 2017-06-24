package order.domain.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import order.domain.Order;

/**
 * @author claudioed on 24/06/17. Project spring
 */
public interface OrderRepository extends ReactiveCassandraRepository<Order,String>{}

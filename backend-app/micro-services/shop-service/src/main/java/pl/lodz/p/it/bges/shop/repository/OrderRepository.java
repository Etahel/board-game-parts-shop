package pl.lodz.p.it.bges.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lodz.p.it.bges.shop.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

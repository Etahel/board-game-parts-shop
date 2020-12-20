package pl.lodz.p.it.bges.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lodz.p.it.bges.shop.entity.Order;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByClientUsername(String client_username, Pageable pageable);

    Optional<Order> findByIdAndClientUsername(Long id, String client_username);

}

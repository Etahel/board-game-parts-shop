package pl.lodz.p.it.bges.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.bges.shop.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}

package pl.lodz.p.it.bges.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.bges.shop.entity.Element;

public interface ElementRepository extends JpaRepository<Element, Long> {
}

package pl.lodz.p.it.bges.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lodz.p.it.bges.shop.entity.Element;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {
}

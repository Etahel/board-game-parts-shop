package pl.lodz.p.it.bges.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.bges.shop.entity.Element;

import java.util.Optional;

public interface ElementRepository extends JpaRepository<Element, Long> {
    Optional<Element> findElementById(Long id);
}

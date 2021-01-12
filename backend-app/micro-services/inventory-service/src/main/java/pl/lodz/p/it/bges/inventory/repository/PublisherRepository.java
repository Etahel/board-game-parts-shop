package pl.lodz.p.it.bges.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lodz.p.it.bges.inventory.entity.Publisher;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    public Boolean existsByName(String name);

    public Optional<Publisher> findByName(String name);
}

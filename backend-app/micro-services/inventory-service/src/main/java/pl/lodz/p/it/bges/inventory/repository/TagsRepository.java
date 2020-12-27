package pl.lodz.p.it.bges.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lodz.p.it.bges.inventory.entity.Tag;

import java.util.Optional;

@Repository
public interface TagsRepository extends JpaRepository<Tag, Long> {
    public Boolean existsByName(String name);

    public Optional<Tag> findByName(String name);
}

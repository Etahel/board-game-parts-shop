package pl.lodz.p.it.bges.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.Publisher;
import pl.lodz.p.it.bges.inventory.entity.Tag;

import java.util.List;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long>, JpaSpecificationExecutor<BoardGame> {
    public List<BoardGame> findAllByTags(Tag tag);

    public List<BoardGame> findAllByPublisher(Publisher publisher);
}

package pl.lodz.p.it.bges.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.Element;

import java.util.List;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long>, JpaSpecificationExecutor<Element> {
    Boolean existsByBoardGame(BoardGame boardGame);

    List<Element> findElementByIdInAndActive(List<Long> ids, Boolean active);

    List<Element> findElementByIdIn(List<Long> ids);
}

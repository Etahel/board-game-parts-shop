package pl.lodz.p.it.bges.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.inventory.criteria.BoardGameCriteria;
import pl.lodz.p.it.bges.inventory.dto.BoardGameDto;
import pl.lodz.p.it.bges.inventory.dto.TagDto;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.Element;
import pl.lodz.p.it.bges.inventory.entity.Tag;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;
import pl.lodz.p.it.bges.inventory.exception.boardgame.BoardGameNotFoundException;
import pl.lodz.p.it.bges.inventory.exception.boardgame.TagExistsException;
import pl.lodz.p.it.bges.inventory.exception.boardgame.TagNotFoundException;
import pl.lodz.p.it.bges.inventory.repository.BoardGameRepository;
import pl.lodz.p.it.bges.inventory.repository.OrderItemRepository;
import pl.lodz.p.it.bges.inventory.repository.TagsRepository;
import pl.lodz.p.it.bges.inventory.repository.specification.BoardGameSpecification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional(rollbackOn = {RuntimeException.class, InventoryException.class})
public class BoardGameService {

    private TagsRepository tagsRepository;
    private BoardGameRepository boardGameRepository;
    private ElementService elementService;
    private OrderItemRepository orderItemRepository;

    @Autowired
    BoardGameService(TagsRepository tagsRepository, BoardGameRepository boardGameRepository, ElementService elementService, OrderItemRepository orderItemRepository) {
        this.tagsRepository = tagsRepository;
        this.boardGameRepository = boardGameRepository;
        this.orderItemRepository = orderItemRepository;
        this.elementService = elementService;
    }

    public void createTag(TagDto tagDto) throws InventoryException {
        if (tagsRepository.existsByName(tagDto.getName())) {
            throw new TagExistsException();
        } else {
            Tag tag = new Tag();
            tagDto.putProperties(tag);
            tagsRepository.save(tag);
        }
    }

    public List<Tag> getTags() {
        return tagsRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Tag getTag(Long id) throws InventoryException {
        Optional<Tag> tagOpt = tagsRepository.findById(id);
        if (tagOpt.isPresent()) {
            return tagOpt.get();
        } else {
            throw new TagNotFoundException();
        }
    }

    public Tag getTag(String name) throws InventoryException {
        Optional<Tag> tagOpt = tagsRepository.findByName(name);
        if (tagOpt.isPresent()) {
            return tagOpt.get();
        } else {
            throw new TagNotFoundException();
        }
    }

    public void updateTag(TagDto tagDto, Long id) throws InventoryException {
        tagDto.patchProperties(getTag(id));
    }

    public void deleteTag(Long id) throws InventoryException {
        Tag tag = getTag(id);
        List<BoardGame> boardGames = boardGameRepository.findAllByTags(tag);
        for (BoardGame boardGame : boardGames) {
            boardGame.getTags().remove(tag);
        }
        tagsRepository.delete(tag);

    }

    public Page<BoardGame> getBoardGames(Pageable pageable, BoardGameCriteria boardGameCriteria) {
        return boardGameRepository.findAll(where(BoardGameSpecification.getBoardGameSpecification(boardGameCriteria)).and(BoardGameSpecification.getActiveSpecification()), pageable);
    }

    public BoardGame getBoardGame(Long id) throws InventoryException {
        Optional<BoardGame> boardGame = boardGameRepository.findById(id);
        if (boardGame.isPresent()) {
            return boardGame.get();
        } else {
            throw new BoardGameNotFoundException();
        }
    }

    public void createBoardGame(BoardGameDto boardGameDto) throws InventoryException {
        BoardGame boardGame = new BoardGame();
        boardGameDto.putProperties(boardGame);
        if (boardGameDto.getTags() != null) {
            for (String tagName : boardGameDto.getTags()) {
                Tag tag = getTag(tagName);
                boardGame.getTags().add(tag);
            }
        }
        boardGameRepository.save(boardGame);
    }

    public void updateBoardGame(BoardGameDto boardGameDto, Long id) throws InventoryException {
        BoardGame boardGame = getBoardGame(id);
        boardGameDto.patchProperties(boardGame);
        if (boardGameDto.getTags() != null) {
            boardGame.getTags().clear();
            for (String tagName : boardGameDto.getTags()) {
                Tag tag = getTag(tagName);
                boardGame.getTags().add(tag);
            }
        }
    }

    public void deleteBoardGame(Long id) throws InventoryException {
        BoardGame boardGame = getBoardGame(id);
        if (boardGame.getElements().isEmpty()) {
            boardGameRepository.delete(boardGame);
        } else {
            boardGame.getElements().removeIf((element -> orderItemRepository.existsByElementId(element.getId())));
            for (Element element : boardGame.getElements()) {
                element.setActive(false);
            }
            if (boardGame.getElements().isEmpty()) {
                boardGameRepository.delete(boardGame);
            } else {
                boardGame.setActive(false);
            }

        }
    }


}

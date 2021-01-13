package pl.lodz.p.it.bges.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.inventory.criteria.BoardGameCriteria;
import pl.lodz.p.it.bges.inventory.dto.BoardGameDto;
import pl.lodz.p.it.bges.inventory.dto.PublisherDto;
import pl.lodz.p.it.bges.inventory.dto.TagDto;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.Element;
import pl.lodz.p.it.bges.inventory.entity.Publisher;
import pl.lodz.p.it.bges.inventory.entity.Tag;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;
import pl.lodz.p.it.bges.inventory.exception.boardgame.*;
import pl.lodz.p.it.bges.inventory.repository.BoardGameRepository;
import pl.lodz.p.it.bges.inventory.repository.OrderItemRepository;
import pl.lodz.p.it.bges.inventory.repository.PublisherRepository;
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
    private PublisherRepository publisherRepository;

    @Autowired
    BoardGameService(TagsRepository tagsRepository, BoardGameRepository boardGameRepository, ElementService elementService,
                     OrderItemRepository orderItemRepository, PublisherRepository publisherRepository) {
        this.tagsRepository = tagsRepository;
        this.boardGameRepository = boardGameRepository;
        this.orderItemRepository = orderItemRepository;
        this.elementService = elementService;
        this.publisherRepository = publisherRepository;

    }

    public void createPublisher(PublisherDto publisherDto) throws InventoryException {
        if (publisherRepository.existsByName(publisherDto.getName())) {
            throw new PublisherExistsException();
        } else {
            Publisher publisher = new Publisher();
            publisherDto.putProperties(publisher);
            publisherRepository.save(publisher);
        }
    }

    public List<Publisher> getPublishers() {
        return publisherRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Publisher getPublisher(Long id) throws InventoryException {
        Optional<Publisher> publisherOpt = publisherRepository.findById(id);
        if (publisherOpt.isPresent()) {
            return publisherOpt.get();
        } else {
            throw new PublisherNotFoundException();
        }
    }

    public Publisher getPublisher(String name) throws InventoryException {
        Optional<Publisher> publisherOpt = publisherRepository.findByName(name);
        if (publisherOpt.isPresent()) {
            return publisherOpt.get();
        } else {
            throw new PublisherNotFoundException();
        }
    }

    public void updatePublisher(PublisherDto publisherDto, Long id) throws InventoryException {
        publisherDto.patchProperties(getPublisher(id));
    }

    public void deletePublisher(Long id) throws InventoryException {
        Publisher publisher = getPublisher(id);
        List<BoardGame> boardGames = boardGameRepository.findAllByPublisher(publisher);
        for (BoardGame boardGame : boardGames) {
            boardGame.setPublisher(null);
        }
        publisherRepository.delete(publisher);

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

    public BoardGame createBoardGame(BoardGameDto boardGameDto) throws InventoryException {
        BoardGame boardGame = new BoardGame();
        boardGameDto.putProperties(boardGame);
        if (boardGameDto.getTags() != null) {
            for (String tagName : boardGameDto.getTags()) {
                Tag tag = getTag(tagName);
                boardGame.getTags().add(tag);
            }
        }

        if (boardGameDto.getPublisher() != null) {
            Publisher publisher = getPublisher(boardGameDto.getPublisher());
            boardGame.setPublisher(publisher);
        }
        return boardGameRepository.save(boardGame);
    }

    public void putBoardGame(BoardGameDto boardGameDto, Long id) throws InventoryException {
        BoardGame boardGame = getBoardGame(id);
        boardGameDto.putProperties(boardGame);
        if (boardGameDto.getTags() != null) {
            boardGame.getTags().clear();
            for (String tagName : boardGameDto.getTags()) {
                Tag tag = getTag(tagName);
                boardGame.getTags().add(tag);
            }
        }

        if (boardGameDto.getPublisher() != null) {
            Publisher publisher = getPublisher(boardGameDto.getPublisher());
            boardGame.setPublisher(publisher);
        }

    }

    public void deleteBoardGame(Long id) throws InventoryException {
        BoardGame boardGame = getBoardGame(id);
        if (boardGame.getElements().isEmpty()) {
            boardGameRepository.delete(boardGame);
        } else {
            boardGame.getElements().removeIf((element -> !orderItemRepository.existsByElementId(element.getId())));
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

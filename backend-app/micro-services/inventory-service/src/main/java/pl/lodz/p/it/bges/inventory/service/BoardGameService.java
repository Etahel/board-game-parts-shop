package pl.lodz.p.it.bges.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.inventory.criteria.BoardGameCriteria;
import pl.lodz.p.it.bges.inventory.dto.TagDto;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.Tag;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;
import pl.lodz.p.it.bges.inventory.exception.boardgame.TagExistsException;
import pl.lodz.p.it.bges.inventory.exception.boardgame.TagNotFoundException;
import pl.lodz.p.it.bges.inventory.repository.BoardGameRepository;
import pl.lodz.p.it.bges.inventory.repository.TagsRepository;
import pl.lodz.p.it.bges.inventory.repository.specification.BoardGameSpecification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = {RuntimeException.class, InventoryException.class})
public class BoardGameService {

    private TagsRepository tagsRepository;
    private BoardGameRepository boardGameRepository;

    @Autowired
    BoardGameService(TagsRepository tagsRepository, BoardGameRepository boardGameRepository) {
        this.tagsRepository = tagsRepository;
        this.boardGameRepository = boardGameRepository;
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

    public void updateTag(TagDto tagDto, Long id) throws InventoryException {
        Optional<Tag> tagOpt = tagsRepository.findById(id);
        if (tagOpt.isPresent()) {
            tagDto.patchProperties(tagOpt.get());
        } else {
            throw new TagNotFoundException();
        }
    }

    public void deleteTag(Long id) throws InventoryException {
        Optional<Tag> tagOpt = tagsRepository.findById(id);
        if (tagOpt.isPresent()) {
            List<BoardGame> boardGames = boardGameRepository.findAllByTags(tagOpt.get());
            for (BoardGame boardGame : boardGames) {
                boardGame.getTags().remove(tagOpt.get());
            }
            tagsRepository.delete(tagOpt.get());
        } else {
            throw new TagNotFoundException();
        }
    }

    public Page<BoardGame> getBoardGames(Pageable pageable, BoardGameCriteria boardGameCriteria) {
        return boardGameRepository.findAll(BoardGameSpecification.getBoardGameSpecification(boardGameCriteria), pageable);
    }
}

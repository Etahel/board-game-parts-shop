package pl.lodz.p.it.bges.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.inventory.criteria.ElementCriteria;
import pl.lodz.p.it.bges.inventory.dto.ElementDto;
import pl.lodz.p.it.bges.inventory.dto.StockDto;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.Element;
import pl.lodz.p.it.bges.inventory.entity.Stock;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;
import pl.lodz.p.it.bges.inventory.exception.boardgame.BoardGameNotFoundException;
import pl.lodz.p.it.bges.inventory.exception.element.ElementNotFoundException;
import pl.lodz.p.it.bges.inventory.exception.element.NegativeStockException;
import pl.lodz.p.it.bges.inventory.repository.BoardGameRepository;
import pl.lodz.p.it.bges.inventory.repository.ElementRepository;
import pl.lodz.p.it.bges.inventory.repository.OrderItemRepository;
import pl.lodz.p.it.bges.inventory.repository.specification.ElementSpecification;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional(rollbackOn = {RuntimeException.class, InventoryException.class})
public class ElementService {

    private ElementRepository elementRepository;
    private BoardGameRepository boardGameRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public ElementService(ElementRepository elementRepository, BoardGameRepository boardGameRepository, OrderItemRepository orderItemRepository) {
        this.elementRepository = elementRepository;
        this.boardGameRepository = boardGameRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Element getElement(Long elementId) throws InventoryException {
        Optional<Element> elementOpt = elementRepository.findById(elementId);
        if (elementOpt.isPresent()) {
            return elementOpt.get();
        } else {
            throw new ElementNotFoundException();
        }
    }

    public Page<Element> getElements(Long boardGameId, Pageable pageable, ElementCriteria elementCriteria) {
        return elementRepository.findAll(where(ElementSpecification.getBoardGameSpecification(boardGameId))
                .and(ElementSpecification.getCriteriaSpecification(elementCriteria)).and(ElementSpecification.getActiveSpecification()), pageable);
    }

    public void patchElement(Long elementId, ElementDto elementDto) throws InventoryException {
        Element element = getElement(elementId);
        elementDto.patchProperties(element);
    }


    private void resizeStock(Stock stock, Integer stockChange) throws InventoryException {
        if (stock.getStockSize() + stockChange < 0) {
            throw new NegativeStockException();
        }
        stock.setStockSize(stock.getStockSize() + stockChange);
    }

    public void patchStock(Long elementId, StockDto stockDto) throws InventoryException {
        Element element = getElement(elementId);
        stockDto.patchProperties(element.getStock());
        resizeStock(element.getStock(), stockDto.getStockChange());
    }

    public void createElement(Long boardGameId, ElementDto elementDto) throws InventoryException {
        Optional<BoardGame> boardGameOpt = boardGameRepository.findById(boardGameId);
        if (boardGameOpt.isPresent()) {
            Element element = new Element();
            elementDto.putProperties(element);
            element.setBoardGame(boardGameOpt.get());
            elementRepository.save(element);
        } else {
            throw new BoardGameNotFoundException();
        }
    }

    public void deleteElement(Long id) throws InventoryException {
        Element element = getElement(id);
        if (!orderItemRepository.existsByElementId(element.getId())) {
            elementRepository.delete(element);
        } else {
            element.setActive(false);
        }
    }


}

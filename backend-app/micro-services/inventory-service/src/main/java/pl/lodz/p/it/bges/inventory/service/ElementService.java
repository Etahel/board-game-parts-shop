package pl.lodz.p.it.bges.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional(rollbackOn = {RuntimeException.class, InventoryException.class})
public class ElementService {

    private ElementRepository elementRepository;
    private BoardGameRepository boardGameRepository;

    @Autowired
    public ElementService(ElementRepository elementRepository, BoardGameRepository boardGameRepository) {
        this.elementRepository = elementRepository;
        this.boardGameRepository = boardGameRepository;
    }

    public Element getElement(Long elementId) throws InventoryException {
        Optional<Element> elementOpt = elementRepository.findById(elementId);
        if (elementOpt.isPresent()) {
            return elementOpt.get();
        } else {
            throw new ElementNotFoundException();
        }
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


}

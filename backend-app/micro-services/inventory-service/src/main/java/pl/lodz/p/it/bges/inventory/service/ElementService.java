package pl.lodz.p.it.bges.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.inventory.entity.Element;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;
import pl.lodz.p.it.bges.inventory.exception.element.ElementNotFoundException;
import pl.lodz.p.it.bges.inventory.exception.element.NegativeStockException;
import pl.lodz.p.it.bges.inventory.repository.ElementRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional(rollbackOn = {RuntimeException.class, InventoryException.class})
public class ElementService {

    private ElementRepository elementRepository;

    @Autowired
    public ElementService(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    public Element getElement(Long elementId) throws InventoryException {
        Optional<Element> elementOpt = elementRepository.findById(elementId);
        if (elementOpt.isPresent()) {
            return elementOpt.get();
        } else {
            throw new ElementNotFoundException();
        }
    }


    public void resizeStock(Long elementId, Integer stockChange) throws InventoryException {
        Element element = getElement(elementId);
        if (element.getStock().getStockSize() + stockChange < 0) {
            throw new NegativeStockException();
        }
        element.getStock().setStockSize(element.getStock().getStockSize() + stockChange);
    }

}

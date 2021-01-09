package pl.lodz.p.it.bges.inventory.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.core.roles.Roles;
import pl.lodz.p.it.bges.inventory.criteria.ElementOrderCriteria;
import pl.lodz.p.it.bges.inventory.dto.ElementDto;
import pl.lodz.p.it.bges.inventory.dto.StockDto;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;
import pl.lodz.p.it.bges.inventory.service.ElementService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/elements")
@RestController
public class ElementController {

    private ElementService elementService;

    @Autowired
    public ElementController(ElementService elementService) {
        this.elementService = elementService;
    }


    @PatchMapping("/{id}/stock")
    @RolesAllowed(Roles.EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public void patchStock(@PathVariable Long id, @RequestBody @JsonView(Views.Modify.class) StockDto stockDto) throws InventoryException {
        elementService.patchStock(id, stockDto);
    }

    @GetMapping("/{id}")
    @RolesAllowed({Roles.USER})
    @JsonView(Views.Details.class)
    public ElementDto getElement(@PathVariable Long id) throws InventoryException {
        return new ElementDto(elementService.getElement(id));
    }

    @PatchMapping("/{id}")
    @RolesAllowed(Roles.EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public void patchElement(@PathVariable Long id, @RequestBody @JsonView(Views.Modify.class) ElementDto elementDto) throws InventoryException {
        elementService.patchElement(id, elementDto);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteElement(@PathVariable Long id) throws InventoryException {
        elementService.deleteElement(id);
    }

    @GetMapping
    @RolesAllowed(Roles.USER)
    public List<ElementDto> getElements(@Valid ElementOrderCriteria elementOrderCriteria) {
        return elementService.findElementsForOrder(elementOrderCriteria).stream().map(ElementDto::new).collect(Collectors.toList());
    }


}

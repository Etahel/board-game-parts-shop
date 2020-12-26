package pl.lodz.p.it.bges.inventory.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.core.roles.Roles;
import pl.lodz.p.it.bges.inventory.dto.StockDto;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;
import pl.lodz.p.it.bges.inventory.service.ElementService;

import javax.annotation.security.RolesAllowed;

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


}

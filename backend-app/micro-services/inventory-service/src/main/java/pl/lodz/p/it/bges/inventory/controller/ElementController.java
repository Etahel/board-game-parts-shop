package pl.lodz.p.it.bges.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.roles.Roles;
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

    @PostMapping("/{id}/stock}")
    @RolesAllowed(Roles.EMPLOYEE)
    public void resizeStock(@PathVariable Long id, @RequestBody Integer stockChange) throws InventoryException {
        elementService.resizeStock(id, stockChange);
    }
}

package pl.lodz.p.it.bges.inventory.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RequestMapping("/stock")
@RestController
public class StockController {

    @PostMapping(path = "/transaction")
    @PermitAll
    public String purchaseStock() {
        return "STOCK PURCHASED";

    }
}

package pl.lodz.p.it.bges.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "stock")
public class Stock extends InventoryEntity {

    @Column(name = "stock_size")
    private Integer stockSize;

    @Column(name = "available")
    public Boolean available;
}

package pl.lodz.p.it.bges.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem extends InventoryEntity {

    @Column(name = "element_id", updatable = false, insertable = false)
    private Long elementId;
}

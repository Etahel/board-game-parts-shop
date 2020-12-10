package pl.lodz.p.it.bges.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
@Entity
@Table(name = "stock")
public class Stock extends InventoryEntity {

    @ManyToOne
    @JoinColumn(name = "element_id", foreignKey = @ForeignKey(name = "fk_stock_elements"), nullable = false)
    private Element element;

    @Column(name = "stock_size")
    @PositiveOrZero
    private Integer stockSize;
}

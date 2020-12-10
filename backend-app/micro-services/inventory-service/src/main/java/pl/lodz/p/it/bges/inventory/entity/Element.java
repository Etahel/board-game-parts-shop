package pl.lodz.p.it.bges.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
@Entity
@Table(name = "elements")
public class Element extends InventoryEntity {

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "price", nullable = false)
    @PositiveOrZero
    private Double price;

    @JoinColumn(name = "default_stock_id", foreignKey = @ForeignKey(name = "fk_elements_stock"), nullable = false, unique = true)
    @OneToOne
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_elements_element_categories"))
    private ElementCategory elementCategory;
}

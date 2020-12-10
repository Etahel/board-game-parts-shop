package pl.lodz.p.it.bges.shop.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter(AccessLevel.PROTECTED)
@Entity
@Table(name = "elements")
public class Element extends ShopEntity {

    @Column(name = "price", nullable = false)
    @PositiveOrZero
    private Double price;

    @JoinColumn(name = "default_stock_id", foreignKey = @ForeignKey(name = "fk_elements_stock"), nullable = false, unique = true)
    @OneToOne
    private Stock stock;

}

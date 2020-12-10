package pl.lodz.p.it.bges.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@Entity
@Table(name = "stock")
public class Stock extends ShopEntity {

    @Column(name = "element_id", nullable = false, updatable = false)
    private Long elementId;

    @Column(name = "stock_size")
    @PositiveOrZero
    private Integer stockSize;


}

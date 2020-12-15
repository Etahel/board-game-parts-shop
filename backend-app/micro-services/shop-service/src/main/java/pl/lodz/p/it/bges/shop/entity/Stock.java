package pl.lodz.p.it.bges.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "stock")
public class Stock extends ShopEntity {

    @Column(name = "stock_size", nullable = false)
    private Integer stockSize;

    @Column(name = "available", nullable = false)
    public Boolean available;


}

package pl.lodz.p.it.bges.shop.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter(AccessLevel.PROTECTED)
@Entity
@Table(name = "elements")
public class Element extends ShopEntity {

    @Column(name = "price", nullable = false)
    private Double price;

    @JoinColumn(name = "stock_id", nullable = false, unique = true)
    @OneToOne(cascade = CascadeType.PERSIST)
    private Stock stock;

    @Column(name = "active")
    private Boolean active;

}

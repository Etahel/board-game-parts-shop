package pl.lodz.p.it.bges.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem extends ShopEntity {

    @JoinColumn(name = "element_id", foreignKey = @ForeignKey(name = "fk_order_items_elements"))
    @OneToOne
    private Element element;


}

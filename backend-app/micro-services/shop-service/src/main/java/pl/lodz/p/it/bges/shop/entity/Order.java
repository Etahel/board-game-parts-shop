package pl.lodz.p.it.bges.shop.entity;

import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.OrderStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order extends ShopEntity {

    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "fk_orders_clients"), nullable = false)
    private Client client;

    @Column(name = "order_first_name", length = 30, nullable = false)
    private String orderFirstName;

    @Column(name = "order_last_name", length = 30, nullable = false)
    private String orderLastName;

    @OneToOne
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "fk_orders_addresses"), nullable = false)
    private Address address;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order_items_orders"))
    private List<OrderItem> orderItems;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "value")
    private Double value;


}

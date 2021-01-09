package pl.lodz.p.it.bges.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order extends ShopEntity {

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "order_first_name", length = 30, nullable = false)
    private String orderFirstName;

    @Column(name = "order_last_name", length = 30, nullable = false)
    private String orderLastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address = new Address();

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "value", nullable = false)
    private Double value;


}

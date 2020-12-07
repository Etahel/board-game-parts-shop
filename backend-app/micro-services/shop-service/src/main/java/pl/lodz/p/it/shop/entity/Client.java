package pl.lodz.p.it.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "CLIENTS")
@Setter
@Getter
public class Client extends ShopEntity {

    @Column(name = "USERNAME", updatable = false, nullable = false)
    private String username;

    @Column(name = "FIRST_NAME", length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", length = 30)
    private String lastName;

    @JoinColumn(name = "ADDRESS_ID")
    @OneToOne(cascade = {CascadeType.ALL})
    private Address defaultAddress;

}

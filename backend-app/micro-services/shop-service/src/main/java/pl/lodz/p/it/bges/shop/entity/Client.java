package pl.lodz.p.it.bges.shop.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = "clients")
public class Client extends ShopEntity {

    public Client() {
    }

    public Client(String username) {
        setUsername(username);
    }

    @Column(name = "username", updatable = false, nullable = false, unique = true)
    @Setter(AccessLevel.PROTECTED)
    private String username;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @JoinColumn(name = "address_id", nullable = false)
    @OneToOne(cascade = {CascadeType.ALL})
    private Address defaultAddress = new Address();

}

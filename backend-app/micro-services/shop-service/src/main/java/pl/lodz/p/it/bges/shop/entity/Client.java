package pl.lodz.p.it.bges.shop.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "CLIENTS")
@Setter
@Getter
public class Client extends ShopEntity {

    public Client() {
        super();
    }

    ;

    public Client(String username) {
        super();
        setUsername(username);
    }

    @Column(name = "USERNAME", updatable = false, nullable = false, unique = true)
    @Setter(AccessLevel.PROTECTED)
    private String username;

    @Column(name = "FIRST_NAME", length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", length = 30)
    private String lastName;

    @JoinColumn(name = "ADDRESS_ID")
    @OneToOne(cascade = {CascadeType.ALL})
    private Address defaultAddress = new Address();

}

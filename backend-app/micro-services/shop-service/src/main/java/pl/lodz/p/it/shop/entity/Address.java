package pl.lodz.p.it.shop.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
@Setter
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "CITY", length = 50)
    private String city;

    @Column(name = "STREET", length = 50)
    private String street;

    @Column(name = "HOUSE_NO", length = 10)
    private String houseNo;

    @Column(name = "FLAT_NO", length = 10)
    private String flatNo;

    @Column(name = "POSTAL_CODE", length = 10)
    private String postalCode;

    @Version
    @Setter(AccessLevel.PROTECTED)
    private Long version;


}

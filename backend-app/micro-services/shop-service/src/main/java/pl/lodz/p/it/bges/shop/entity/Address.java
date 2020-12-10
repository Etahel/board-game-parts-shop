package pl.lodz.p.it.bges.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "ADDRESSES")
@Setter
@Getter
public class Address extends ShopEntity {

    @Column(name = "CITY", length = 50)
    private String city;

    @Column(name = "STREET", length = 50)
    private String street;

    @Column(name = "HOUSE_NO", length = 10)
    private String houseNo;

    @Column(name = "FLAT_NO", length = 10)
    private String flatNo;

    @Column(name = "POSTAL_CODE", length = 6)
    @Pattern(regexp = "^\\d{2}[- ]\\d{3}$")
    private String postalCode;


}

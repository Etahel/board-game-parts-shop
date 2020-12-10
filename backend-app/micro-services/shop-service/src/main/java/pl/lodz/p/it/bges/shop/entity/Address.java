package pl.lodz.p.it.bges.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;


@Setter
@Getter
@Entity
@Table(name = "addresses")
public class Address extends ShopEntity {

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "street", length = 50)
    private String street;

    @Column(name = "house_no", length = 10)
    private String houseNo;

    @Column(name = "flat_no", length = 10)
    private String flatNo;

    @Column(name = "postal_code", length = 6, nullable = false)
    @Pattern(regexp = "^\\d{2}[- ]\\d{3}$")
    private String postalCode;


}

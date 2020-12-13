package pl.lodz.p.it.bges.shop.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.shop.entity.Address;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddressDto extends ShopDto<Address> {

    @NotEmpty
    @Size(max = 50)
    @JsonView(Views.Address.class)
    private String city;
    @JsonView(Views.Address.class)
    private String street;
    @JsonView(Views.Address.class)
    private String houseNo;
    @JsonView(Views.Address.class)
    private String flatNo;

    @NotEmpty
    @Size(max = 6)
    @Pattern(regexp = "^\\d{2}[- ]\\d{3}$")
    @JsonView(Views.Address.class)
    private String postalCode;

    public AddressDto() {
    }


    public AddressDto(Address address) {
        super(address);
    }

    @Override
    public void fillProperties(Address entity) {
        super.fillProperties(entity);
        setCity(entity.getCity());
        setStreet(entity.getStreet());
        setHouseNo(entity.getHouseNo());
        setFlatNo(entity.getFlatNo());
        setPostalCode(entity.getPostalCode());
    }

    @Override
    public void putProperties(Address entity) {
        super.putProperties(entity);
        entity.setCity(getCity());
        entity.setStreet(getStreet());
        entity.setHouseNo(getHouseNo());
        entity.setFlatNo(getFlatNo());
        entity.setPostalCode(getPostalCode());
    }

    @Override
    public void patchProperties(Address entity) {
        super.patchProperties(entity);
        if (getCity() != null && !getCity().isBlank()) {
            entity.setCity(getCity());
        }
        if (getStreet() != null && !getStreet().isBlank()) {
            entity.setStreet(getStreet());
        }
        if (getHouseNo() != null && !getHouseNo().isBlank()) {
            entity.setHouseNo(getHouseNo());
        }
        if (getFlatNo() != null && !getFlatNo().isBlank()) {
            entity.setFlatNo(getFlatNo());
        }
        if (getPostalCode() != null && !getPostalCode().isBlank()) {
            entity.setPostalCode(getPostalCode());
        }
    }
}

package pl.lodz.p.it.bges.shop.dto;

import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.shop.entity.Address;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddressDto extends ShopDto<Address> {

    @NotEmpty
    @Size(max = 50)
    private String city;
    private String street;
    private String houseNo;
    private String flatNo;

    @NotEmpty
    @Size(max = 6)
    private String postalCode;

    public AddressDto() {
    }

    ;

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

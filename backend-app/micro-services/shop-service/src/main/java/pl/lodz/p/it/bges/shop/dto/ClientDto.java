package pl.lodz.p.it.bges.shop.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.shop.entity.Client;

@Getter
@Setter
public class ClientDto extends ShopDto<Client> {

    @JsonView(Views.Internal.class)
    private String username;

    @JsonView(Views.Client.class)
    private String firstName;
    @JsonView(Views.Client.class)
    private String lastName;
    @JsonView(Views.ClientWithAddress.class)
    private AddressDto address;

    public ClientDto() {
    }

    ;

    public ClientDto(Client client) {
        super(client);
    }


    @Override
    public void fillProperties(Client entity) {
        super.fillProperties(entity);
        setUsername(entity.getUsername());
        setFirstName(entity.getFirstName());
        setLastName(entity.getLastName());
        if (entity.getDefaultAddress() != null) {
            setAddress(new AddressDto(entity.getDefaultAddress()));
        }

    }

    @Override
    public void putProperties(Client entity) {
        super.putProperties(entity);
        entity.setFirstName(getFirstName());
        entity.setLastName(getLastName());
        if (getAddress() != null) {
            getAddress().putProperties(entity.getDefaultAddress());
        }
    }

    @Override
    public void patchProperties(Client entity) {
        super.patchProperties(entity);
        if (getFirstName() != null && !getFirstName().isBlank()) {
            entity.setFirstName(getFirstName());
        }
        if (getLastName() != null && !getLastName().isBlank()) {
            entity.setLastName(getLastName());
        }
        if (entity.getDefaultAddress() != null) {
            getAddress().patchProperties(entity.getDefaultAddress());
        }
    }
}

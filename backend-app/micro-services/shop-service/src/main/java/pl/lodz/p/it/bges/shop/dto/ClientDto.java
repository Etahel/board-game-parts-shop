package pl.lodz.p.it.bges.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.shop.entity.Client;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ClientDto extends ShopDto<Client> {

    @JsonView(Views.Basic.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String username;

    @JsonView(Views.Basic.class)
    @Size(max = 50)
    private String firstName;
    @JsonView(Views.Basic.class)
    @Size(max = 50)
    private String lastName;
    @JsonView(Views.Normal.class)
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
        if (getFirstName() != null) {
            entity.setFirstName(getFirstName());
        }
        if (getLastName() != null) {
            entity.setLastName(getLastName());
        }
        if (entity.getDefaultAddress() != null) {
            getAddress().patchProperties(entity.getDefaultAddress());
        }
    }
}

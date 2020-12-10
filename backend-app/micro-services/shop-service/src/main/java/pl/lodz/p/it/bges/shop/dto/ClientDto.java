package pl.lodz.p.it.bges.shop.dto;

import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.shop.entity.Client;

@Getter
@Setter
public class ClientDto extends ShopDto<Client> {

    private String username;
    private String firstName;
    private String lastName;
    private AddressDto address;

    public ClientDto() {
    }

    ;

    public ClientDto(Client client) {
        super(client);
    }


    @Override
    public void init() {
        this.address = new AddressDto();
    }

    @Override
    public void fillProperties(Client entity) {
        super.fillProperties(entity);
        setUsername(entity.getUsername());
        setFirstName(entity.getFirstName());
        setLastName(entity.getLastName());
        getAddress().fillProperties(entity.getDefaultAddress());
    }

    @Override
    public void putProperties(Client entity) {
        super.putProperties(entity);
        entity.setFirstName(getFirstName());
        entity.setLastName(getLastName());
        getAddress().putProperties(entity.getDefaultAddress());
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
        getAddress().patchProperties(entity.getDefaultAddress());
    }
}

package pl.lodz.p.it.account.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.idm.UserRepresentation;
import pl.lodz.p.it.account.dto.AbstractDto;

@Setter
@Getter
public class UserBaseDto extends AbstractDto<UserRepresentation> {

    private String firstName;
    private String lastName;


    public UserBaseDto() {
    }


    public UserBaseDto(UserRepresentation entity) {
        super(entity);
    }


    @Override
    public void putProperties(UserRepresentation entity) {
        entity.setFirstName(getFirstName());
        entity.setLastName(getLastName());
    }

    @Override
    public void patchProperties(UserRepresentation entity) {
        if (getFirstName() != null) {
            entity.setFirstName(getFirstName());
        }
        if (getLastName() != null) {
            entity.setLastName(getLastName());
        }
    }

    @Override
    public void fillProperties(UserRepresentation entity) {
        setFirstName(entity.getFirstName());
        setLastName(entity.getLastName());
    }

}

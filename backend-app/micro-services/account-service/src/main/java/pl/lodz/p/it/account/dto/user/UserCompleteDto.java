package pl.lodz.p.it.account.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.ArrayList;

@Getter
@Setter
public class UserCompleteDto extends UserDetailsDto {


    private String password;

    public UserCompleteDto() {
    }


    public UserCompleteDto(UserRepresentation entity) {
        super(entity);
    }

    @Override
    public void putProperties(UserRepresentation entity) {
        super.putProperties(entity);

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(getPassword());

        entity.setCredentials(new ArrayList<CredentialRepresentation>());
        entity.getCredentials().add(passwordCred);
    }

    @Override
    public void fillProperties(UserRepresentation entity) {
        super.fillProperties(entity);
    }
}

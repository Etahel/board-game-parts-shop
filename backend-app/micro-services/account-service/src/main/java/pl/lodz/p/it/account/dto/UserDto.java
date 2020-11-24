package pl.lodz.p.it.account.dto;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Getter
@Setter
public class UserDto extends AbstractDto<UserRepresentation> {

    @NotEmpty
    private String username;
    private String firstName;
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;

    public UserDto() {
    }

    ;

    public UserDto(UserRepresentation entity) {
        super(entity);
    }

    @Override
    public void putProperties(UserRepresentation entity) {
        entity.setUsername(getUsername());
        entity.setEmail(getEmail());
        entity.setFirstName(getFirstName());
        entity.setLastName(getLastName());

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(getPassword());

        entity.setCredentials(new ArrayList<CredentialRepresentation>());
        entity.getCredentials().add(passwordCred);
    }

    @Override
    public void fillProperties(UserRepresentation entity) {
        setUsername(entity.getUsername());
        setEmail(entity.getEmail());
        setFirstName(entity.getFirstName());
        setLastName(entity.getLastName());
    }
}

package pl.lodz.p.it.bges.account.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import pl.lodz.p.it.bges.account.dto.AccountDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Getter
@Setter
public class UserDto extends AccountDto<UserRepresentation> {

    public UserDto(UserRepresentation entity) {
        super(entity);
    }

    public UserDto() {
    }

    @NotEmpty
    @JsonView(value = {UserView.Details.class})
    private String username;
    @NotEmpty
    @Email
    @JsonView(value = {UserView.Details.class})
    private String email;

    @JsonView(value = {UserView.Complete.class})
    private String password;

    @Override
    public void putProperties(UserRepresentation entity) {
        super.putProperties(entity);
        entity.setUsername(getUsername());
        entity.setEmail(getEmail());

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
        setUsername(entity.getUsername());
        setEmail(entity.getEmail());
    }
}

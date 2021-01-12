package pl.lodz.p.it.bges.account.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import pl.lodz.p.it.bges.account.dto.AccountDto;
import pl.lodz.p.it.bges.account.dto.Views;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Getter
@Setter
public class UserDto extends AccountDto<UserRepresentation> {

    public UserDto(UserRepresentation entity) {
        super(entity);
    }

    public UserDto() {
    }

    @NotBlank
    @Size(min = 3, max = 15)
    @JsonView(value = {Views.User.class})
    private String username;
    @NotEmpty
    @Email
    @JsonView(value = {Views.User.class})
    private String email;

    @JsonView(value = {Views.UserSecret.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

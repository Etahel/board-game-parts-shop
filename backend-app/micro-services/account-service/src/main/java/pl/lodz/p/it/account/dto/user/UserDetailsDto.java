package pl.lodz.p.it.account.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.idm.UserRepresentation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserDetailsDto extends UserBaseDto {

    public UserDetailsDto(UserRepresentation entity) {
        super(entity);
    }

    public UserDetailsDto() {
    }

    @NotEmpty
    private String username;
    @NotEmpty
    @Email
    private String email;

    @Override
    public void putProperties(UserRepresentation entity) {
        super.putProperties(entity);
        entity.setUsername(getUsername());
        entity.setEmail(getEmail());
    }

    @Override
    public void fillProperties(UserRepresentation entity) {
        super.fillProperties(entity);
        setUsername(entity.getUsername());
        setEmail(entity.getEmail());
    }
}

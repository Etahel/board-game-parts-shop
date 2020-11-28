package pl.lodz.p.it.account.dto.error;

import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.account.dto.AbstractDto;
import pl.lodz.p.it.account.exception.AccountException;

@Getter
@Setter
public class ErrorDto extends AbstractDto<AccountException> {

    private String errorMessage;

    public ErrorDto() {
    }

    ;

    public ErrorDto(AccountException entity) {
        super(entity);
    }

    @Override
    public void fillProperties(AccountException entity) {
        setErrorMessage(entity.getMessage());
    }
}

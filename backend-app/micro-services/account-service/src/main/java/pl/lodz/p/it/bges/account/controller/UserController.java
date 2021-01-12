package pl.lodz.p.it.bges.account.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.account.dto.Views;
import pl.lodz.p.it.bges.account.dto.user.UserDto;
import pl.lodz.p.it.bges.account.exception.AccountException;
import pl.lodz.p.it.bges.account.service.UserService;
import pl.lodz.p.it.bges.core.roles.Roles;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.QueryParam;
import java.security.Principal;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @PostMapping(path = "/register")
    @PermitAll
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@JsonView(value = Views.UserSecret.class) @RequestBody @Valid UserDto userCompleteDto) throws AccountException {
        userService.registerUser(userCompleteDto);
    }

    @GetMapping(path = "/me")
    @RolesAllowed({Roles.USER, Roles.EMPLOYEE})
    @JsonView(value = Views.User.class)
    public UserDto getMe(Principal principal) throws AccountException {
        return new UserDto(userService.getUser(principal.getName()));
    }

    @PostMapping(path = "/reset-password")
    @PermitAll
    @ResponseStatus(value = HttpStatus.OK)
    public void resetPassword(@QueryParam("username") String username) throws AccountException {
        userService.sendResetPasswordEmail(username);
    }

    @PostMapping(path = "/resend-verify-email")
    @PermitAll
    @ResponseStatus(value = HttpStatus.OK)
    public void resendVerifyEmail(@QueryParam("username") String username) throws AccountException {
        userService.sendVerifyEmail(username);
    }

    @PostMapping(path = "/change-email")
    @RolesAllowed({Roles.USER, Roles.EMPLOYEE})
    @ResponseStatus(value = HttpStatus.OK)
    public void changeEmail(Principal principal, @QueryParam("email") @Email @NotEmpty String email) throws AccountException {
        userService.changeEmail(principal.getName(), email);
    }


}

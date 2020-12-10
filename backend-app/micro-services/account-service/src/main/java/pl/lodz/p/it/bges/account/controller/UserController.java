package pl.lodz.p.it.bges.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.account.dto.user.UserCompleteDto;
import pl.lodz.p.it.bges.account.dto.user.UserDetailsDto;
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

    @GetMapping(path = "/test")
    @RolesAllowed(Roles.USER)
    public String test(Principal principal) {
        return principal.getName();
    }

    @PostMapping(path = "/register")
    @PermitAll
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@RequestBody @Valid UserCompleteDto userCompleteDto) throws AccountException {
        userService.registerUser(userCompleteDto);
    }

    @GetMapping(path = "/me")
    @RolesAllowed(Roles.USER)
    public UserDetailsDto getMe(Principal principal) throws AccountException {
        return new UserDetailsDto(userService.getUser(principal.getName()));
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
    @RolesAllowed(Roles.USER)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeEmail(Principal principal, @QueryParam("email") @Email @NotEmpty String email) throws AccountException {
        userService.changeEmail(principal.getName(), email);
    }


}

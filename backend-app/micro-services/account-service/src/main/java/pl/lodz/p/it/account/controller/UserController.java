package pl.lodz.p.it.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.account.common.Roles;
import pl.lodz.p.it.account.dto.user.UserBaseDto;
import pl.lodz.p.it.account.dto.user.UserCompleteDto;
import pl.lodz.p.it.account.dto.user.UserDetailsDto;
import pl.lodz.p.it.account.exception.AccountException;
import pl.lodz.p.it.account.properties.RolesProperties;
import pl.lodz.p.it.account.service.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.security.Principal;

@RequestMapping("/user")
@RestController
@EnableConfigurationProperties(RolesProperties.class)
public class UserController {


    private RolesProperties rolesProperties;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

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

    @PatchMapping(path = "/me")
    @RolesAllowed(Roles.USER)
    @ResponseStatus(value = HttpStatus.OK)
    public void patchMe(Principal principal, @RequestBody UserBaseDto userDetails) throws AccountException {
        userService.patchUser(principal.getName(), userDetails);
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


}

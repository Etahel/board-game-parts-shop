package pl.lodz.p.it.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.account.common.Roles;
import pl.lodz.p.it.account.dto.UserDto;
import pl.lodz.p.it.account.exception.AccountException;
import pl.lodz.p.it.account.properties.RolesProperties;
import pl.lodz.p.it.account.service.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
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
    public void register(@RequestBody @Valid UserDto userDto) throws AccountException {
        userService.registerUser(userDto);
    }

    @GetMapping(path = "/me")
    @RolesAllowed(Roles.USER)
    public UserDto getMe(Principal principal) throws AccountException {
        return new UserDto(userService.getUser(principal.getName()));
    }


}

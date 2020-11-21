package pl.lodz.p.it.account.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
@ConfigurationProperties(prefix = "roles")
@Getter
@Setter
public class RolesProperties {

    private String user;
    private String employee;
    private String admin;
}

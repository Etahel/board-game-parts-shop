package pl.lodz.p.it.bges.account.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
@ConfigurationProperties(prefix = "keycloak-admin")
@Getter
@Setter
public class KeycloakAdminProperties {

    private String realm;
    private String serverUrl;
    private String resource;
    private String secret;

}

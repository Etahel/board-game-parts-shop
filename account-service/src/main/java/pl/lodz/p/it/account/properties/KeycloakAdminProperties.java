package pl.lodz.p.it.account.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

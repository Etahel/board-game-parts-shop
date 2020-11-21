package pl.lodz.p.it.account.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import pl.lodz.p.it.account.properties.KeycloakAdminProperties;

@Configuration
@EnableConfigurationProperties(KeycloakAdminProperties.class)
public class KeycloakConfig {

    @Autowired
    private KeycloakAdminProperties properties;

    @Bean
    @RequestScope
    public Keycloak keycloak () {
        return KeycloakBuilder.builder()
                .serverUrl(properties.getServerUrl())
                .realm(properties.getRealm())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(properties.getResource())
                .clientSecret(properties.getSecret())
                .build();
    }
}

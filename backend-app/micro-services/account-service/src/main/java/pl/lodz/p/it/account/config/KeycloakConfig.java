package pl.lodz.p.it.account.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import pl.lodz.p.it.account.exception.keycloak.KeycloakConnectionException;
import pl.lodz.p.it.account.properties.KeycloakAdminProperties;

@Configuration
@EnableConfigurationProperties(KeycloakAdminProperties.class)
public class KeycloakConfig {

    public enum EmailAction {
        UPDATE_PASSWORD("UPDATE_PASSWORD"),
        VERIFY_EMAIL("VERIFY_EMAIL");

        private final String action;

        EmailAction(final String action) {
            this.action = action;
        }

        @Override
        public String toString() {
            return action;
        }
    }

    @Autowired
    private KeycloakAdminProperties properties;

    Logger logger = LoggerFactory.getLogger(KeycloakConfig.class);

    @Bean
    @RequestScope
    public Keycloak keycloak() throws KeycloakConnectionException {
        try {
            return KeycloakBuilder.builder()
                    .serverUrl(properties.getServerUrl())
                    .realm(properties.getRealm())
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .clientId(properties.getResource())
                    .clientSecret(properties.getSecret())
                    .build();
        } catch (Exception e) {
            logger.error("Could not create Keycloack client instance: " + e.getMessage());
            throw new KeycloakConnectionException();
        }
    }
}

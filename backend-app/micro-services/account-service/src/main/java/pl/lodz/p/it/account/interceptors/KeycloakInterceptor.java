package pl.lodz.p.it.account.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pl.lodz.p.it.account.service.KeycloakService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class KeycloakInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private KeycloakService keycloakService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        keycloakService.closeConnection();
    }
}

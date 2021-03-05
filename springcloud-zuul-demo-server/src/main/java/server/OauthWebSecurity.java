package server;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoDefaultConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration
@EnableOAuth2Sso
public class OauthWebSecurity extends OAuth2SsoDefaultConfiguration {
    public OauthWebSecurity(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring()
                .antMatchers("/actuator", "/actuator/**");
    }
}

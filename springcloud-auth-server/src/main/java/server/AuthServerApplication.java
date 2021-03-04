package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class AuthServerApplication {
    public static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";
    @Autowired
    private ResourceServerTokenServices tokenServices;

    @GetMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authorization = request.getHeader("authorization");
        if (StringUtils.isEmpty(authorization)) {
            return null;
        }
        if (!authorization.startsWith(AUTHENTICATION_SCHEME_BEARER)) {
            return null;
        }
        OAuth2Authentication user = tokenServices.loadAuthentication(authorization.substring(7));
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}

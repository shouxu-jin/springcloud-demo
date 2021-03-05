package customer.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients
@EnableResourceServer
@SpringBootApplication
public class CustomerClientApplication {
    @Autowired
    private ProductClient productClient;

    @RequestMapping("/hello")
    public String hello() {
        return "nice ";
    }

    @RequestMapping("/world")
    public String world() {
        return "nice " + productClient.world();
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerClientApplication.class, args);
    }
}

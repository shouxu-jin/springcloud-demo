package customer.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@SpringBootApplication
public class CustomerServerApplication {
    @Value("${version}")
    private String version;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @GetMapping("/hello")
    public String hello() {
        String key = String.valueOf(Math.random());
        redisTemplate.boundValueOps(key).set("SB");
        return "hello " + version + " " + redisTemplate.boundValueOps(key).get();
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerServerApplication.class, args);
    }
}

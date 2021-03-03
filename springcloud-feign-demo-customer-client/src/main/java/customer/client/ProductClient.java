package customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "zuul-server", fallback = ProductClientImpl.class)
public interface ProductClient {
    @GetMapping("/hello")
    String hello();
    @GetMapping("/world")
    String world();
}

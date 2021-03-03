package customer.client;

import org.springframework.stereotype.Component;

@Component
public class ProductClientImpl implements ProductClient {
    @Override
    public String hello() {
        return "hello from hystrix";
    }

    @Override
    public String world() {
        return "word from hystrix";
    }
}

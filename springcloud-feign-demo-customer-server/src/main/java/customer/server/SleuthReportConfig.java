package customer.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;
import zipkin2.reporter.Reporter;

@Configuration
public class SleuthReportConfig {
    private static final Logger logger = LoggerFactory.getLogger("SLEUTH-LOG");

    @Bean
    public Reporter<Span> reporter() {
        return span -> logger.info(span.toString());
    }
}

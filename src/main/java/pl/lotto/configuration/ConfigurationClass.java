package pl.lotto.configuration;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}

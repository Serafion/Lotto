package pl.lotto.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;

@Configuration
public class ConfigurationClass {

    @Bean("clock1")
    @Profile("default")
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}

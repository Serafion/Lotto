package pl.lotto;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.Clock;


@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public Clock clockMock() {
        return new TestClock();
    }
}

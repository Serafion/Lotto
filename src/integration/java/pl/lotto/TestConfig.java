package pl.lotto;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;


@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public Clock clockMock() {
        return new MutableClock(LocalDateTime.of(2022, 02, 12, 10, 11, 00).atZone(ZoneId.systemDefault()));
    }

}

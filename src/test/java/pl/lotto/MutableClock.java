package pl.lotto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Configuration
@Profile("test")
public class MutableClock extends Clock {
    public volatile ZonedDateTime today;

    @Bean
    public MutableClock MutableClock() {
        return new MutableClock(ZonedDateTime.now());
    }

    public MutableClock(ZonedDateTime today) {
        this.today = today;
    }

    @Override
    public ZoneId getZone() {
        return today.getZone();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return new MutableClock(today.withZoneSameInstant(zone));
    }

    @Override
    public Instant instant() {
        return today.toInstant();
    }

    public synchronized void addDays(long days) {
        today = today.plusDays(days);
    }
}

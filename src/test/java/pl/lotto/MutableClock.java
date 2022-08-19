package pl.lotto;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MutableClock extends Clock {
    public volatile ZonedDateTime today;

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

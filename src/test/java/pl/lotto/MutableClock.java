package pl.lotto;

import java.time.*;


public class MutableClock extends Clock {
    public volatile ZonedDateTime today;


    public Clock clock() {
        return new MutableClock(ZonedDateTime.of(LocalDateTime.of(2022, 02, 12, 10, 10), ZoneId.systemDefault()));
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

package pl.lotto.winningnumbergenerator.timer;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

import static pl.lotto.winningnumbergenerator.util.Constants.*;

public class DrawTimer implements TimeSettable {
    Clock clock;

    public DrawTimer(Clock clock) {
        this.clock = clock;
    }

    @Override
    public boolean itsTimeToMakeADraw(LocalDateTime dateTime) {



        Clock offset = Clock.offset(Clock.fixed(Instant.from(LocalDateTime.of())), Duration.ofHours(1L));


        return isDrawDate(dateTime) && currentTime().isAfter(dateTime);
    }

    private boolean isDrawDate(LocalDateTime dateTime) {
        return dateTime.getDayOfWeek().equals(DRAW_DAY_OF_WEEK) && dateTime.getHour() == HOUR_OF_DRAW && dateTime.getMinute() == MINUTE_OF_DRAW && dateTime.getSecond() == SECOND_OF_DRAW;
    }

    @Override
    public LocalDateTime currentTime() {
        return Instant.now(clock).atZone(clock.getZone()).toLocalDateTime();
//        return Instant.from(clock.instant()).atZone(clock.getZone()).toLocalDateTime();
    }
}

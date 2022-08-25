package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.LocalDateTime;

import static pl.lotto.numberreceiver.Constants.*;

class DateGenerator {

    private final Clock clock;

    public DateGenerator(Clock clock) {
        this.clock = clock;
    }

    public LocalDateTime retrieveNextDrawDate() {
        LocalDateTime currentTime = clock.instant().atZone(clock.getZone()).toLocalDateTime();
        return fetchDate(currentTime);
    }

    private LocalDateTime fetchDate(LocalDateTime currentTime) {
        LocalDateTime drawDay = retrieveDayOfDraw(currentTime.getDayOfMonth(), currentTime);
        while (!drawDay.getDayOfWeek().equals(DRAW_DAY_OF_WEEK) || (drawDay.compareTo(currentTime) < 0)) {
            drawDay = drawDay.plusDays(1);
        }
        return drawDay;
    }

    private LocalDateTime retrieveDayOfDraw(int dayOfDraw, LocalDateTime currentTime) {
        return LocalDateTime.of(currentTime.getYear(), currentTime.getMonth(), dayOfDraw, HOUR_OF_DRAW, MINUTE_OF_DRAW, SECOND_OF_DRAW);
    }
}

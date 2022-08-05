package pl.lotto.numberreceiver.dategenerator;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

import static pl.lotto.numberreceiver.constants.Constants.*;

public class DateGenerator implements Clockable {

    private final Clock clock;

    public DateGenerator(Clock clock) {
        this.clock = clock;
    }

    @Override
    public Long retrieveNextDrawDate() {
        Long currentTime = clock.instant().toEpochMilli();
        return fetchDrawDateInEpochMili(currentTime);
    }

    private Long fetchDrawDateInEpochMili(Long currentTime) {
        LocalDateTime drawDay = retrieveDayOfDraw(convertToLocalDateTime(currentTime).getDayOfMonth(), currentTime);
        while (!drawDay.getDayOfWeek().equals(DRAW_DAY_OF_WEEK) || (drawDay.compareTo(convertToLocalDateTime(currentTime)) < 0)) {
            drawDay = drawDay.plusDays(1);
        }
        Instant instant = drawDay.atZone(clock.getZone()).toInstant();
        return instant.toEpochMilli();
    }

    private LocalDateTime retrieveDayOfDraw(int dayOfDraw, Long currentDateInEpochMili) {
        LocalDateTime currentTime = Instant.ofEpochMilli(currentDateInEpochMili).atZone(clock.getZone()).toLocalDateTime();
        return LocalDateTime.of(currentTime.getYear(), currentTime.getMonth(), dayOfDraw, HOUR_OF_DRAW, MINUTE_OF_DRAW
                , SECOND_OF_DRAW);
    }

    private LocalDateTime convertToLocalDateTime(Long currentTime) {
        return Instant.ofEpochMilli(currentTime)
                .atZone(clock.getZone()).toLocalDateTime();
    }
}

package pl.lotto.numberreceiver.dategenerator;

import java.time.LocalDateTime;
import static pl.lotto.numberreceiver.constants.Constants.DRAW_DAY_OF_WEEK;
import static pl.lotto.numberreceiver.constants.Constants.HOUR_OF_DRAW;
import static pl.lotto.numberreceiver.constants.Constants.MINUTE_OF_DRAW;
import static pl.lotto.numberreceiver.constants.Constants.SECOND_OF_DRAW;

public class DateGenerator {

    private final LocalDateTime currentTime = LocalDateTime.now();

    public LocalDateTime retrieveNextDrawDate() {
        LocalDateTime dateOfDraw = retrieveDayOfDraw(currentTime.getDayOfMonth());
        while (!dateOfDraw.getDayOfWeek().equals(DRAW_DAY_OF_WEEK) || (dateOfDraw.compareTo(currentTime) < 0)) {
            dateOfDraw = dateOfDraw.plusDays(1);
        }
        return dateOfDraw;
    }

    private LocalDateTime retrieveDayOfDraw(int dayOfDraw) {
        return LocalDateTime.of(currentTime.getYear(), currentTime.getMonth(), dayOfDraw, HOUR_OF_DRAW, MINUTE_OF_DRAW
                , SECOND_OF_DRAW);
    }
}

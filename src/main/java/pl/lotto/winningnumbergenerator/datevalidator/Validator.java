package pl.lotto.winningnumbergenerator.datevalidator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Validator {
    public boolean validateDateOfDraw(LocalDateTime date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                && date.getHour() == (12)
                && date.getMinute() == 0
                && date.getSecond() == 0;
    }
}

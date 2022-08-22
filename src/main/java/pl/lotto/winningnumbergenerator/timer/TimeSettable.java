package pl.lotto.winningnumbergenerator.timer;

import java.time.LocalDateTime;

public interface TimeSettable {
    boolean itsTimeToMakeADraw(LocalDateTime dateTime);
    LocalDateTime currentTime();

}

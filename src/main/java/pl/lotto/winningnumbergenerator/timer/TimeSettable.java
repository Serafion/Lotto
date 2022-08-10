package pl.lotto.winningnumbergenerator.timer;

import java.time.LocalDateTime;

public interface TimeSettable {
    boolean ItsTimeToMakeADraw(LocalDateTime dateTime);
    LocalDateTime currentTime();

}

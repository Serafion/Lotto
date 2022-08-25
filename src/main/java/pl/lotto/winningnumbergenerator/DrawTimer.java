package pl.lotto.winningnumbergenerator;

import java.time.Clock;
import java.time.LocalDateTime;

class DrawTimer {
    Clock clock;

    public DrawTimer(Clock clock) {
        this.clock = clock;
    }

    public boolean itsTimeToMakeADraw(LocalDateTime dateTime) {
        return dateTime.isBefore(currentTime());
    }


    public LocalDateTime currentTime() {
        return LocalDateTime.now(clock);
    }
}

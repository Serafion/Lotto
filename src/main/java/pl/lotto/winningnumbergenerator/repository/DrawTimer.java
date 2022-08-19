package pl.lotto.winningnumbergenerator.repository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DrawTimer {
    private final Clock clock;

    private final Set<LocalDateTime> drawDates = new HashSet<>();

    public DrawTimer(Clock clock) {
        this.clock = clock;
    }


    public boolean ItsTimeToMakeADraw(LocalDateTime dateTime) {
        return isDrawDate(dateTime) && currentTime().isAfter(dateTime);
    }

    public void addDrawDate(LocalDateTime dateTime) {
        drawDates.add(dateTime);
    }

    public void updateDrawDates(List<LocalDateTime> list) {
        for (LocalDateTime dateTime : list) {
            addDrawDate(dateTime);
        }
    }

    private LocalDateTime currentTime() {
        return LocalDateTime.now(clock);
    }

    private boolean isDrawDate(LocalDateTime dateTime) {
        return drawDates.contains(dateTime);
    }
}

package pl.lotto.winningnumbergenerator;

import java.time.LocalDateTime;
import java.util.List;

public interface NumberProvidable {
    List<Integer> retrieveWonNumbersForDate(LocalDateTime dateTime);
}

package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.List;

public interface NumberProvider {
    List<Integer> getWinningNumbers(LocalDateTime localDateTime);
}

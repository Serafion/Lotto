package pl.lotto.winningnumbergenerator.repository;

import java.time.LocalDateTime;
import java.util.List;

public interface WinningNumbersService {
    List<Integer> provideWinningNumbers(LocalDateTime dateTime);
    List<Integer> saveWinningNumbers(LocalDateTime dateTime, List<Integer> winningNumbers);

    boolean containsWinningNumbers(LocalDateTime dateTime);
}

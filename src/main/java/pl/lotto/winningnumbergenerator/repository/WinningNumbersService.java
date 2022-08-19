package pl.lotto.winningnumbergenerator.repository;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.List;

public interface WinningNumbersService {
    NumberReceiverResultDto provideWinningNumbers(LocalDateTime dateTime);

    NumberReceiverResultDto saveWinningNumbers(LocalDateTime dateTime, List<Integer> winningNumbers);

    boolean containsWinningNumbers(LocalDateTime dateTime);

    void saveNextDrawDate(LocalDateTime dateTime);

    boolean isValidDrawDate(LocalDateTime dateTime);
}

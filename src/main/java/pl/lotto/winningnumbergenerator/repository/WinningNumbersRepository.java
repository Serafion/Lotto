package pl.lotto.winningnumbergenerator.repository;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.List;

public interface WinningNumbersRepository {
    NumberReceiverResultDto retrieveArchivalDraw(LocalDateTime dateTime);

    NumberReceiverResultDto saveWinningNumbers(LocalDateTime dateTime, List<Integer> numbers);

    boolean containsWinningNumbers(LocalDateTime dateTime);

    List<LocalDateTime> returnDrawDates();

}

package pl.lotto.winningnumbergenerator.repository;

import pl.lotto.winningnumbergenerator.winningnumbersdto.WinningNumbersDto;

import java.time.LocalDateTime;

public interface WinningNumbersRepository {
    WinningNumbersDto retrieveArchivalDraw(LocalDateTime dateTime);

    WinningNumbersDto saveWinningNumbers(WinningNumbersDto winningNumbersDto);

    boolean containsWinningNumbers(LocalDateTime dateTime);

}

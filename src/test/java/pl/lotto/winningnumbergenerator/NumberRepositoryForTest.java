package pl.lotto.winningnumbergenerator;

import pl.lotto.winningnumbergenerator.repository.WinningNumbersRepository;
import pl.lotto.winningnumbergenerator.winningnumbersdto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.HashMap;

public class NumberRepositoryForTest implements WinningNumbersRepository {
    WinningNumbersTestConstants constants = new WinningNumbersTestConstants();
    HashMap<LocalDateTime, WinningNumbersDto> wonNumbers;

    public NumberRepositoryForTest() {
        wonNumbers = constants.map;
    }


    @Override
    public WinningNumbersDto saveWinningNumbers(WinningNumbersDto winningNumbersDto) {
        return wonNumbers.put(winningNumbersDto.drawDate(), winningNumbersDto);
    }

    @Override
    public WinningNumbersDto retrieveArchivalDraw(LocalDateTime dateTime) {
        return wonNumbers.get(dateTime);
    }

    @Override
    public boolean containsWinningNumbers(LocalDateTime dateTime) {
        return wonNumbers.containsKey(dateTime);
    }
}

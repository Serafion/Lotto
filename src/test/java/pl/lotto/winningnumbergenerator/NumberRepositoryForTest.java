package pl.lotto.winningnumbergenerator;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class NumberRepositoryForTest implements WinningNumbersRepository {
    TestConstants constants = new TestConstants();
    HashMap<LocalDateTime, NumberReceiverResultDto> wonNumbers;
    List<LocalDateTime> drawDates;

    public NumberRepositoryForTest() {
        wonNumbers = constants.getMap();
        drawDates = constants.getDrawDates();
    }

    @Override
    public List<LocalDateTime> returnDrawDates() {
        return drawDates;
    }

    @Override
    public NumberReceiverResultDto retrieveArchivalDraw(LocalDateTime dateTime) {
        return wonNumbers.get(dateTime);
    }

    @Override
    public NumberReceiverResultDto saveWinningNumbers(LocalDateTime dateTime, List<Integer> numbers) {
        return wonNumbers.put(dateTime, new NumberReceiverResultDto("", Optional.empty(), numbers, Optional.of(dateTime)));
    }

    @Override
    public boolean containsWinningNumbers(LocalDateTime dateTime) {
        return wonNumbers.containsKey(dateTime);
    }
}

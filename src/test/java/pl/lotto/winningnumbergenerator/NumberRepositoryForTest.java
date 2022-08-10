package pl.lotto.winningnumbergenerator;

import pl.lotto.winningnumbergenerator.repository.WinningNumbersRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static pl.lotto.numberreceiver.util.Constants.*;

public class NumberRepositoryForTest implements WinningNumbersRepository {
    HashMap<LocalDateTime,List<Integer>> wonNumbers;

    public NumberRepositoryForTest() {
        wonNumbers = new HashMap<>();
        LocalDateTime dateOfDraw = LocalDateTime.of(2022, 8, 13, HOUR_OF_DRAW, MINUTE_OF_DRAW
                ,SECOND_OF_DRAW);
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        wonNumbers.put(dateOfDraw,list);
    }

    @Override
    public List<Integer> retrieveArchivalDraw(LocalDateTime dateTime) {
        return wonNumbers.get(dateTime);
    }

    @Override
    public List<Integer> saveWinningNumbers(LocalDateTime dateTime, List<Integer> numbers) {
        return wonNumbers.put(dateTime,numbers);
    }

    @Override
    public boolean containsWinningNumbers(LocalDateTime dateTime) {
        return wonNumbers.containsKey(dateTime);
    }
}

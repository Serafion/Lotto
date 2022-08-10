package pl.lotto.winningnumbergenerator.generator;

import pl.lotto.winningnumbergenerator.repository.WinningNumbersService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static pl.lotto.winningnumbergenerator.util.Constants.*;

public class NumberGenerator implements Randomable {


    Set<Integer> numbersGenerated = new HashSet<>();
    Random random = new Random();
    WinningNumbersService numbersService;

    public NumberGenerator(WinningNumbersService numbersService) {

        this.numbersService = numbersService;

    }

    public List<Integer> generateNumbersToRepository(LocalDateTime dateTime) {
        return numbersService.saveWinningNumbers(dateTime,fetchNumbers());
    }

    private List<Integer> fetchNumbers() {
        while (numbersGenerated.size() < NUMBERS_TO_DRAW) {
            numbersGenerated.add(random.nextInt(LOW_NUMBER_BOUNDARY, HIGH_NUMBER_BOUNDARY));
        }
        return numbersGenerated.stream().toList();
    }
}

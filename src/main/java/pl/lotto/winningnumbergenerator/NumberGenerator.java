package pl.lotto.winningnumbergenerator;

import pl.lotto.winningnumbergenerator.winningnumbersdto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static pl.lotto.winningnumbergenerator.Constants.*;

class NumberGenerator {


    Set<Integer> numbersGenerated = new HashSet<>();
    Random random = new Random();

    public NumberGenerator() {

    }

    public WinningNumbersDto generateNumbers(LocalDateTime dateTime) {
        return WinningNumbersMapper.toDto(fetchNumbers(), dateTime);
    }

    private List<Integer> fetchNumbers() {
        while (numbersGenerated.size() < NUMBERS_TO_DRAW) {
            numbersGenerated.add(random.nextInt(LOW_NUMBER_BOUNDARY, HIGH_NUMBER_BOUNDARY));
        }
        return numbersGenerated.stream().toList();
    }
}

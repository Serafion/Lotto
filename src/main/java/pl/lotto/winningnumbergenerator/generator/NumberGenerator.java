package pl.lotto.winningnumbergenerator.generator;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.*;

import static pl.lotto.winningnumbergenerator.util.Constants.*;

public class NumberGenerator implements Randomable {


    Set<Integer> numbersGenerated = new HashSet<>();
    Random random = new Random();

    public NumberGenerator() {

    }

    public NumberReceiverResultDto generateNumbers(LocalDateTime dateTime) {
        return new NumberReceiverResultDto("", Optional.empty(), fetchNumbers(), Optional.of(dateTime));
    }

    private List<Integer> fetchNumbers() {
        while (numbersGenerated.size() < NUMBERS_TO_DRAW) {
            numbersGenerated.add(random.nextInt(LOW_NUMBER_BOUNDARY, HIGH_NUMBER_BOUNDARY));
        }
        return numbersGenerated.stream().toList();
    }
}

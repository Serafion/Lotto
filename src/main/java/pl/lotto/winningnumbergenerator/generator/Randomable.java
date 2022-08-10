package pl.lotto.winningnumbergenerator.generator;

import java.time.LocalDateTime;
import java.util.List;

public interface Randomable {
    List<Integer> generateNumbersToRepository(LocalDateTime dateTime);
}

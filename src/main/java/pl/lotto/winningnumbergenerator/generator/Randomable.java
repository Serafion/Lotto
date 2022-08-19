package pl.lotto.winningnumbergenerator.generator;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;

public interface Randomable {
    NumberReceiverResultDto generateNumbers(LocalDateTime dateTime);
}

package pl.lotto.numberreceiver.repository;

import java.time.LocalDateTime;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.Clock;
import java.util.List;

public interface InputService {
    void addToCurrentNumberList(NumberReceiverResultDto dto);
    List<NumberReceiverResultDto> provideNumbersForDate(LocalDateTime dateOfDraw);
}

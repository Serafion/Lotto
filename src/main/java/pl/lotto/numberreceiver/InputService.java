package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface InputService {
    void addToCurrentNumberList(NumberReceiverResultDto dto);

    List<NumberReceiverResultDto> provideNumbersForDate(LocalDateTime dateOfDraw);

    LocalDateTime provideDrawDate(UUID uuid);
}

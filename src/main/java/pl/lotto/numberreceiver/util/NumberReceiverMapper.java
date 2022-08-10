package pl.lotto.numberreceiver.util;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.validator.ValidateMessage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NumberReceiverMapper {

    public static NumberReceiverResultDto toDto(ValidateMessage validationMessage, Optional<UUID> uuid, List<Integer> numbersFromUser, Optional<LocalDateTime> dateOfDraw) {
        return new NumberReceiverResultDto(validationMessage, uuid, numbersFromUser, dateOfDraw);
    }
}

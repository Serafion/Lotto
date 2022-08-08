package pl.lotto.numberreceiver.util;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.validator.ValidateMessage;

public class NumberReceiverMapper {

    public static NumberReceiverResultDto toDto(ValidateMessage validationMessage, Optional<UUID> uuid, List<Integer> numbersFromUser, Optional<Long> dateOfDraw) {
        return new NumberReceiverResultDto(validationMessage, uuid, numbersFromUser, dateOfDraw);
    }
}

package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class NumberReceiverMapper {

    public static NumberReceiverResultDto toDto(String validationMessage, Optional<UUID> uuid, List<Integer> numbersFromUser, Optional<LocalDateTime> dateOfDraw) {
        return new NumberReceiverResultDto(validationMessage, uuid, numbersFromUser, dateOfDraw);
    }
}

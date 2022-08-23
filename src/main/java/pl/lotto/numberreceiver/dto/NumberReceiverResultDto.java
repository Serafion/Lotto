package pl.lotto.numberreceiver.dto;

import pl.lotto.numberreceiver.validator.ValidateMessage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record NumberReceiverResultDto(ValidateMessage message,
                                      Optional<UUID> uniqueLotteryId,
                                      List<Integer> userNumbers,
                                      Optional<LocalDateTime> dateOfDraw) {
}

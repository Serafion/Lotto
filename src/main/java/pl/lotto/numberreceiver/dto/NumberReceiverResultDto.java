package pl.lotto.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record NumberReceiverResultDto(String message, Optional<UUID> uniqueLotteryId,
                                      List<Integer> userNumbers, Optional<LocalDateTime> dateOfDraw) {
}

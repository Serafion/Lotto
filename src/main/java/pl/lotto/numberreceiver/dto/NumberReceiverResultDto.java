package pl.lotto.numberreceiver.dto;

import java.util.Optional;
import java.util.UUID;

public record NumberReceiverResultDto(String message, Optional<UUID> uniqueLotteryId) {
}

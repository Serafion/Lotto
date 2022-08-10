package pl.lotto.numberreceiver.repository;

import pl.lotto.numberreceiver.validator.ValidateMessage;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public record UserInput(ValidateMessage message, List<Integer> numbersFromUser, UUID uniqueLotteryID,
                        LocalDateTime drawDate) implements Serializable {
}

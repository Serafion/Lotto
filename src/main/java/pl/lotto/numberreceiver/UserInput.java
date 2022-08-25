package pl.lotto.numberreceiver;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


record UserInput(String message, List<Integer> numbersFromUser, UUID uniqueLotteryID,
                 LocalDateTime drawDate) implements Serializable {
}

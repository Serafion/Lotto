package pl.lotto.numberreceiver.datastorage;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


public record UserInput(String message, List<Integer> numbersFromUser, UUID uniqueLotteryID) implements Serializable {
}

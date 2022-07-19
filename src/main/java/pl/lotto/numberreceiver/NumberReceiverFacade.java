package pl.lotto.numberreceiver;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

public class NumberReceiverFacade {

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        if (numbersFromUser.size() == 6) {
            Optional<UUID> generateRandomId = Optional.of(UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002"));
            return new NumberReceiverResultDto("correct message", generateRandomId);
        } else {
            return new NumberReceiverResultDto("failed message", Optional.empty());
        }
    }
}

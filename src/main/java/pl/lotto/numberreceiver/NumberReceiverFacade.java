package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerator;
import pl.lotto.numberreceiver.validator.NumberValidatorFacade;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NumberReceiverFacade {
    private final NumberValidatorFacade numberValidatorFacade = new NumberValidatorFacade();
    private final Randomable randomable;

    public NumberReceiverFacade() {
        this.randomable = new UuidGenerator();
    }

    //Constructor for test purposes
    public NumberReceiverFacade(Randomable randomable) {
        this.randomable = randomable;
    }

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        if (numbersFromUser == null) {
            return new NumberReceiverResultDto("error", Optional.empty());
        }
        String message = numberValidatorFacade.generateMessageFromReceivedInput(numbersFromUser);
        Optional<UUID> uuid = randomable.generateOptionalUuid(message);
        return new NumberReceiverResultDto(message, uuid);
    }
}

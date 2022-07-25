package pl.lotto.numberreceiver;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerable;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerator;
import pl.lotto.numberreceiver.validator.ValidatorModel;

public class NumberReceiverFacade {
    private final ValidatorModel validator;
    private final UuidGenerable generator;

    NumberReceiverFacade(ValidatorModel validator, UuidGenerable generator) {
        this.validator = validator;
        this.generator = generator;
    }

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        String message = validator.retriveMessageForGivenInput(numbersFromUser);
        Optional<UUID> uuid = generator.generateRandom(message);
        return new NumberReceiverResultDto(message, uuid);
    }
}

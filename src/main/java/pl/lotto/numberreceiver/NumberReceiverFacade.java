package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dategenerator.Clockable;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.repository.InputService;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerable;
import pl.lotto.numberreceiver.validator.Validable;
import pl.lotto.numberreceiver.validator.ValidateMessage;

import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NumberReceiverFacade {
    private final Validable validator;
    private final UuidGenerable generator;
    private final Clockable dateGenerator;
    private final InputService storage;


    public NumberReceiverFacade(Validable validator, UuidGenerable generator, Clockable dateGenerator, InputService storage) {
        this.validator = validator;
        this.generator = generator;
        this.dateGenerator = dateGenerator;
        this.storage = storage;
    }

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        ValidateMessage validationMessage = validator.retrieveMessageForGivenInput(numbersFromUser);
        return fetchDto(numbersFromUser, validationMessage);
    }

    private NumberReceiverResultDto fetchDto(List<Integer> numbersFromUser, ValidateMessage validationMessage) {
        if (!validationMessage.equals(ValidateMessage.CORRECT_MESSAGE)) {
            return EmptyDto(numbersFromUser, validationMessage);
        }
        return ValidDto(numbersFromUser, validationMessage);
    }

    private NumberReceiverResultDto EmptyDto(List<Integer> numbersFromUser, ValidateMessage validationMessage) {
        return new NumberReceiverResultDto(validationMessage, Optional.empty(), numbersFromUser, Optional.empty());
    }

    private NumberReceiverResultDto ValidDto(List<Integer> numbersFromUser, ValidateMessage validationMessage) {
        Optional<UUID> uuid = generator.generateRandom();
        Optional<Long> dateOfDraw = Optional.of(dateGenerator.retrieveNextDrawDate());
        NumberReceiverResultDto dto = new NumberReceiverResultDto(validationMessage, uuid, numbersFromUser, dateOfDraw);
        storage.addToCurrentNumberList(dto);
        return dto;
    }

    public Long outputDrawTime() {
        return dateGenerator.retrieveNextDrawDate();
    }

    public List<NumberReceiverResultDto> retrieveNumbersForDate(Clock date) {
        return storage.provideNumbersForDate(date);
    }
}

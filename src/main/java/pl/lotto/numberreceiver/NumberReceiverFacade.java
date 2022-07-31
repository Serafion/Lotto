package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.datastorage.*;
import pl.lotto.numberreceiver.dategenerator.DateGenerator;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.messageprovider.MessageProvider;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerable;
import pl.lotto.numberreceiver.validator.Validator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NumberReceiverFacade {
    private final Validator validator;
    private final UuidGenerable generator;
    private final DateGenerator dateGenerator = new DateGenerator();
    private final MessageProvider messenger = new MessageProvider();

    private final DataStorage storage;



    NumberReceiverFacade(Validator validator, UuidGenerable generator, DataStorage storage) {

        this.validator = validator;
        this.generator = generator;
        this.storage = storage;
    }

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        String validationMessage = validator.retriveMessageForGivenInput(numbersFromUser);
        Optional<UUID> uuid = generator.generateRandom(validationMessage);
        String message = messenger.provideMessage(numbersFromUser, uuid, validationMessage);
        NumberReceiverResultDto dto = new NumberReceiverResultDto(message, uuid, numbersFromUser);
        storage.addToCurrentNumberList(dto);
        storage.closeStorage();
        return dto;
    }

    public LocalDateTime outputDrawTime() {
        return dateGenerator.retrieveNextDrawDate();
    }

    public List<NumberReceiverResultDto> retrieveNumbersForDate(LocalDateTime date) {
        return storage.provideNumbersForDate(date);
    }
}

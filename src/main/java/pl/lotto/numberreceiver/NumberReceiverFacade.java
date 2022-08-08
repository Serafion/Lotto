package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pl.lotto.numberreceiver.dategenerator.DateGenerator;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.repository.InputService;
import pl.lotto.numberreceiver.util.NumberReceiverMapper;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerable;
import pl.lotto.numberreceiver.validator.Validable;
import pl.lotto.numberreceiver.validator.ValidateMessage;

public class NumberReceiverFacade {
    private final Validable validator;
    private final UuidGenerable generator;
    private final DateGenerator dateGenerator;
    private final InputService storage;


    public NumberReceiverFacade(Validable validator, UuidGenerable generator, DateGenerator dateGenerator, InputService storage) {
        this.validator = validator;
        this.generator = generator;
        this.dateGenerator = dateGenerator;
        this.storage = storage;
    }

    public List<NumberReceiverResultDto> retrieveNumbersForDate(LocalDateTime date) {
        return storage.provideNumbersForDate(date);
    }

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        ValidateMessage validationMessage = validator.retrieveMessageForGivenInput(numbersFromUser);
        return fetchDto(numbersFromUser, validationMessage);
    }

    private NumberReceiverResultDto fetchDto(List<Integer> numbersFromUser, ValidateMessage validationMessage) {
        if (!validationMessage.equals(ValidateMessage.CORRECT_MESSAGE)) {
            return emptyDto(numbersFromUser, validationMessage);
        }
        return validDto(numbersFromUser, validationMessage);
    }

    private NumberReceiverResultDto emptyDto(List<Integer> numbersFromUser, ValidateMessage validationMessage) {
        return new NumberReceiverResultDto(validationMessage, Optional.empty(), numbersFromUser, Optional.empty());
    }

    private NumberReceiverResultDto validDto(List<Integer> numbersFromUser, ValidateMessage validationMessage) {
        Optional<UUID> uuid = generator.generateRandom();
        Optional<Long> dateOfDraw = Optional.of(dateGenerator.retrieveNextDrawDate());
        NumberReceiverResultDto dto = NumberReceiverMapper.toDto(validationMessage, uuid, numbersFromUser, dateOfDraw);
        storage.addToCurrentNumberList(dto);
        return dto;
    }

    public Long outputDrawTime() {
        return dateGenerator.retrieveNextDrawDate();
    }
}

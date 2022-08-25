package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NumberReceiverFacade {
    private final Validator validator;
    private final UuidGenerable generator;
    private final DateGenerator dateGenerator;
    private final InputService storage;


    public NumberReceiverFacade(Validator validator, UuidGenerable generator, DateGenerator dateGenerator, InputService storage) {
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
        return NumberReceiverMapper.toDto(validationMessage.toString(), Optional.empty(), numbersFromUser, Optional.empty());
    }

    private NumberReceiverResultDto validDto(List<Integer> numbersFromUser, ValidateMessage validationMessage) {
        Optional<UUID> uuid = generator.generateRandom();
        Optional<LocalDateTime> dateOfDraw = Optional.of(dateGenerator.retrieveNextDrawDate());
        NumberReceiverResultDto dto = NumberReceiverMapper.toDto(validationMessage.toString(), uuid, numbersFromUser, dateOfDraw);
        storage.addToCurrentNumberList(dto);
        return dto;
    }

    public LocalDateTime outputDrawTime(UUID uuid) {
        return storage.provideDrawDate(uuid);
    }
}

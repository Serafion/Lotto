package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.repository.UserInput;
import pl.lotto.numberreceiver.repository.UserInputRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class NumberReceiverFacade {
    private final Validator validator;
    private final UuidGenerable generator;
    private final DateGenerator dateGenerator;
    private final UserInputRepository userInputRepository;


    public NumberReceiverFacade(Validator validator, UuidGenerable generator, DateGenerator dateGenerator, UserInputRepository userInputRepository) {
        this.validator = validator;
        this.generator = generator;
        this.dateGenerator = dateGenerator;
        this.userInputRepository = userInputRepository;
    }

    public List<NumberReceiverResultDto> retrieveNumbersForDate(LocalDateTime date) {
        List<UserInput> queryResults = userInputRepository.findAllByDate(date);
        return queryResults.stream().map(x -> RecordMapper.toDto(x)).collect(Collectors.toList());
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
        return new NumberReceiverResultDto(validationMessage.toString(), Optional.empty(), numbersFromUser, Optional.empty());
    }

    private NumberReceiverResultDto validDto(List<Integer> numbersFromUser, ValidateMessage validationMessage) {
        Optional<UUID> uuid = generator.generateRandom();
        Optional<LocalDateTime> dateOfDraw = Optional.of(dateGenerator.retrieveNextDrawDate());
        NumberReceiverResultDto dto = new NumberReceiverResultDto(validationMessage.toString(), uuid, numbersFromUser, dateOfDraw);
        userInputRepository.save(RecordMapper.fromDto(dto));
        return dto;
    }

    public LocalDateTime outputDrawTime(UUID uuid) {
        UserInput id = userInputRepository.findById(uuid)
                .orElseThrow( () -> new UserInputNotFoundException("user input not found"));

        return id.isPresent() ?
                Optional.of(id.get().date())
                :
                Optional.empty();
    }
}

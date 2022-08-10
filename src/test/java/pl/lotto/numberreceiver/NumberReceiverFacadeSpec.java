package pl.lotto.numberreceiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.repository.UserInputRepository;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerable;
import pl.lotto.numberreceiver.validator.ValidateMessage;

import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeSpec {

    @Test
    @DisplayName("should accept numbers from user and return correct message when entered six numbers in range")
    public void should_return_correct_message() {
        // given
        UuidGenerable uuidGenerator = new UuidGeneratorForTests();
        Clock clock = Clock.fixed(Instant.ofEpochMilli(1659772800000L), ZoneId.systemDefault());
        UserInputRepository storage = new UserInputRepositoryTest();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests(uuidGenerator, storage, clock);
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(ValidateMessage.CORRECT_MESSAGE, Optional.of(UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002")), numbersFromUser, Optional.of(LocalDateTime.ofInstant(Instant.ofEpochMilli(1659780000000L),clock.getZone())));
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Should return 3 sets of numbers")
    public void should_return_saved_numbers() {
        // given
        UuidGenerable uuidGenerator = new UuidGeneratorForTests();
        Clock clock = Clock.fixed((LocalDateTime.of(2022,8,9,12,0,0).atZone(ZoneId.systemDefault()).toInstant()),ZoneId.systemDefault());
        UserInputRepository storage = new UserInputRepositoryTest();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests(uuidGenerator,storage,clock);
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> numbersFromUser2 = Arrays.asList(1, 2, 5, 4, 8, 6);
        List<Integer> numbersFromUser3 = Arrays.asList(1, 2, 3, 18, 5, 24);
        numberReceiverFacade.inputNumbers(numbersFromUser);
        numberReceiverFacade.inputNumbers(numbersFromUser2);
        numberReceiverFacade.inputNumbers(numbersFromUser3);
        LocalDateTime dateOfDraw = LocalDateTime.of(2022,8,13,12,0,0);

        // when
        List<NumberReceiverResultDto> result = numberReceiverFacade.retrieveNumbersForDate(dateOfDraw);

        // then
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("should return failed message due to less than six numbers message")
    public void should_return_failed_message_if_reveived_less_then_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildDefaultModuleForProduction();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(ValidateMessage.NOT_SIX_NUMBERS, Optional.empty(), numbersFromUser, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message if numbers from user exceed number range 1-99")
    public void should_return_failed_message_if_numbers_not_in_range() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildDefaultModuleForProduction();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 121);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(ValidateMessage.NUMBERS_OUT_OF_RANGE, Optional.empty(), numbersFromUser, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message if provided with more then six numbers")
    public void should_return_failed_message_if_received_more_then_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildDefaultModuleForProduction();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(ValidateMessage.NOT_SIX_NUMBERS, Optional.empty(), numbersFromUser, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message if provided with a list containing duplicates")
    public void should_return_failed_message_if_list_contains_duplicates() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildDefaultModuleForProduction();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 2, 4, 5, 6, 7);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(ValidateMessage.CONTAINS_DUPLICATES, Optional.empty(), numbersFromUser, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }


}

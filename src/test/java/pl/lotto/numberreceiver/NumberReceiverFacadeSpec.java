package pl.lotto.numberreceiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.datastorage.DataStorage;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.messageprovider.MessageProvider;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.lotto.numberreceiver.constants.Constants.*;

class NumberReceiverFacadeSpec {

    @Test
    @DisplayName("should accept numbers from user and return correct message when entered six numbers in range")
    public void should_return_correct_message() {
        // given
        UuidGenerable uuidGenerator = new UuidGeneratorForTests();
        DataStorage storage = new DataStorage(LocalDateTime.of(2022,8,6,12,0,0),new SaverTest(), new LoaderTest());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests(uuidGenerator, storage);
        MessageProvider messageProvider = new MessageProvider();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult =
                new NumberReceiverResultDto(messageProvider.provideMessage(numbersFromUser,
                        Optional.of(
                                UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002")),
                        "validationMessage"),
                        Optional.of(UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002")),
                        numbersFromUser
                        );
        assertThat(result).isEqualTo(expectedResult);
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
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS, Optional.empty(),numbersFromUser);
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
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(FAILED_CONTAINING_NUMBER_NOT_IN_RANGE, Optional.empty(),numbersFromUser);
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
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS, Optional.empty(),numbersFromUser);
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
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(FAILED_CONTAINS_A_DUPLICATE_NUMBER, Optional.empty(),numbersFromUser);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message if provided with no numbers")
    public void should_return_failed_message_if_did_not_receive_any_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildDefaultModuleForProduction();
        List<Integer> numbersFromUser = emptyList();

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS, Optional.empty(),numbersFromUser);
        assertThat(result).isEqualTo(expectedResult);
    }
    @Test
    @DisplayName("should return numbers from user in list")
    public void should_return_numbers_inputed_to_base() {
        // given
        UuidGenerable uuidGenerator = new UuidGeneratorForTests();
        DataStorage storage = new DataStorage(LocalDateTime.of(2022,8,6,12,0,0),new SaverTest(), new LoaderTest());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests(uuidGenerator, storage);
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);
        numberReceiverFacade.inputNumbers(numbersFromUser);

        // when
        List<NumberReceiverResultDto> result = numberReceiverFacade.retrieveNumbersForDate(LocalDateTime.of(2022,8,6,12,0,0));

        // then
//        NumberReceiverResultDto expectedResult =
//                new NumberReceiverResultDto(messageProvider.provideMessage(numbersFromUser,
//                        Optional.of(
//                                UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002")),
//                        "validationMessage"),
//                        Optional.of(UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002")),
//                        numbersFromUser
//                );
        assertThat(result.size()).isEqualTo(1);
    }

}

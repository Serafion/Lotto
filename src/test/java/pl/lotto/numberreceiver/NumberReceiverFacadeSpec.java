package pl.lotto.numberreceiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.configuration.Configuration;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class NumberReceiverFacadeSpec {



    @Test
    @DisplayName("should accept numbers from user and return correct message when entered six numbers in range")
    public void should_return_correct_message() {
        // given
        UuidGenerator uuidGenerator = mock(UuidGenerator.class);
        given(uuidGenerator.generateOptionalUuid(Configuration.CORRECT_MESSAGE)).willReturn(Optional.of(UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002")));
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(uuidGenerator);
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(Configuration.CORRECT_MESSAGE, Optional.of(UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002")));
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message due to less than six numbers message")
    public void should_return_failed_message_if_reveived_less_then_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(Configuration.FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message if numbers from user exceed number range 1-99")
    public void should_return_failed_message_if_numbers_not_in_range() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 121);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(Configuration.FAILED_CONTAINING_NUMBER_NOT_IN_RANGE, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message if provided with more then six numbers")
    public void should_return_failed_message_if_received_more_then_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(Configuration.FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message if provided with a list containing duplicates")
    public void should_return_failed_message_if_list_contains_duplicates() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 2, 4, 5, 6, 7);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(Configuration.FAILED_CONTAINS_A_DUPLICATE_NUMBER, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message if provided with 10 numbers")
    public void should_return_failed_message_if_received_more_then_ten_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(Configuration.FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }
    @Test
    @DisplayName("should return failed message if provided with no numbers")
    public void should_return_failed_message_if_did_not_receive_any_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        List<Integer> numbersFromUser = List.of();

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(Configuration.FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }
    @Test
    @DisplayName("should return failed message if provided with no numbers - null condition")
    public void should_return_failed_message_if_got_null_value() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        List<Integer> numbersFromUser = null;

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(Configuration.FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS, Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }

}

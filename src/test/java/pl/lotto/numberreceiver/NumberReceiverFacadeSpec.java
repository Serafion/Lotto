package pl.lotto.numberreceiver;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeSpec {

    @Test
    @DisplayName("should accept numbers from user and return correct message")
    public void should_return_correct_message() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("correct message", Optional.of(UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002")));
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message due to less than six numbers message")
    public void should_return_failed_message_message() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5);

        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("failed message", Optional.empty());
        assertThat(result).isEqualTo(expectedResult);
    }

}

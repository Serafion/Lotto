package pl.lotto.resultchecker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.util.NumberReceiverMapper;
import pl.lotto.numberreceiver.validator.ValidateMessage;
import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;
import pl.lotto.winningnumbergenerator.WiningNumbersGeneratorFacade;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
class ResultCheckerFacadeTest {

    @Test
    @DisplayName("Should return lists of won numbers which contain hit 6 numbers")
    void should_return_list_of_winning_number() {
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        WiningNumbersGeneratorFacade numbersGeneratorFacade = mock(WiningNumbersGeneratorFacade.class);
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 8, 15, 12, 0, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.systemDefault());
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().buildModuleForTest(numberReceiverFacade, numbersGeneratorFacade, clock, new InputRepositoryTest());
        LocalDateTime dateTime = LocalDateTime.of(2022, 8, 13, 12, 0, 0);
        UUID uuid = UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8e");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<NumberReceiverResultDto> dtoList = List.of(NumberReceiverMapper.toDto(ValidateMessage.CORRECT_MESSAGE, Optional.of(UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8e")), numbers, Optional.of(dateTime)));
        given(numberReceiverFacade.retrieveNumbersForDate(dateTime)).willReturn(dtoList);
        given(numbersGeneratorFacade.retrieveWonNumbersForDate(dateTime)).willReturn(numbers);
        Map<UUID, WonNumbersCount> expected = new HashMap<>();
        expected.put(uuid, WonNumbersCount.SIX_NUMBERS_HIT);

        //when
        Map<UUID, WonNumbersCount> result = resultCheckerFacade.checkWinners(uuid);


        //then
        assertThat(result).isEqualTo(expected);
    }

}
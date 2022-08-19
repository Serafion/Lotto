package pl.lotto.resultchecker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;
import pl.lotto.winningnumbergenerator.WiningNumbersGeneratorFacade;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
class ResultCheckerFacadeTest {

    Constants constants = new Constants();
    @InjectMocks
    NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
    WiningNumbersGeneratorFacade numbersGeneratorFacade = mock(WiningNumbersGeneratorFacade.class);
//    @Test
//    @DisplayName("Should return lists of won numbers which contain hit 6 numbers")
//    void should_return_list_of_winning_number() {
//        //Given
//        Clock clock = Clock.fixed(LocalDateTime.of(2022, 8, 15, 12, 0, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.systemDefault());
//        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().buildModuleForTest(numberReceiverFacade, numbersGeneratorFacade, clock, new InputRepositoryTest());
//        LocalDateTime dateTime = LocalDateTime.of(2022, 8, 13, 12, 0, 0);
//        UUID uuid = UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8e");
//        given(numberReceiverFacade.retrieveNumbersForDate(dateTime)).willReturn(constants.resultsList);
//        given(numbersGeneratorFacade.retrieveWonNumbersForDate(dateTime)).willReturn(constants.winningNumbers);
//        Map<UUID, WonNumbersCount> expected = new HashMap<>();
//        expected.put(uuid, WonNumbersCount.SIX_NUMBERS_HIT);
//
//        //When
//        Map<UUID, WonNumbersCount> result = resultCheckerFacade.checkWinners(uuid);
//
//
//        //Then
//        assertThat(result).isEqualTo(expected);
//    }

    @Test
    @DisplayName("Should return lists of won numbers which contain hit six numbers and five numbers")
    void should_return_list_of_with_two_winning_numbers() {
        //Given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().buildModuleForTest(numberReceiverFacade, numbersGeneratorFacade, constants.clock, new InputRepositoryTest());
        given(numberReceiverFacade.retrieveNumbersForDate(constants.dateOfDraw)).willReturn(constants.resultsList);
        given(numbersGeneratorFacade.retrieveWonNumbersForDate(constants.dateOfDraw)).willReturn(constants.winningNumbers);
        given(numberReceiverFacade.outputDrawTime()).willReturn(constants.dateOfDraw).willReturn(constants.dateOfDrawAfterDraw);


        //When
        Map<UUID, WonNumbersCount> expected = new HashMap<>();
        expected.put(constants.uuid, WonNumbersCount.SIX_NUMBERS_HIT);
        expected.put(constants.uuid1, WonNumbersCount.FIVE_NUMBERS_HIT);
        resultCheckerFacade.checkWinners(constants.uuid);
        constants.clock.addDays(7);
        Map<UUID, WonNumbersCount> result = resultCheckerFacade.checkWinners(constants.uuid);


        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should return empty map when invalid uuid provided")
    void should_empty_map_when_invalid_uuid() {
        //Given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().buildModuleForTest(numberReceiverFacade, numbersGeneratorFacade, constants.clock, new InputRepositoryTest());
        given(numberReceiverFacade.retrieveNumbersForDate(constants.dateOfDraw)).willReturn(constants.resultsList);
        given(numbersGeneratorFacade.retrieveWonNumbersForDate(constants.dateOfDraw)).willReturn(constants.winningNumbers);
        given(numberReceiverFacade.outputDrawTime()).willReturn(constants.dateOfDraw).willReturn(constants.dateOfDrawAfterDraw);


        //When
        Map<UUID, WonNumbersCount> result = resultCheckerFacade.checkWinners(constants.uuid);


        //Then
        assertThat(result).isEmpty();
    }
}
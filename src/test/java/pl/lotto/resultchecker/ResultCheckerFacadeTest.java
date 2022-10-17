package pl.lotto.resultchecker;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lotto.infrastructure.httpclient.resultchecker.NumberProviderClient;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.checkerdto.CheckerDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

//To do add new test to check additional behaviour ex. returning LocalDateTime.MIN and MAX

@ExtendWith(MockitoExtension.class)
class ResultCheckerFacadeTest implements SampleUserUuid {

    Constants constants = new Constants();
    @InjectMocks
    NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
    NumberProviderClient numberProvider = mock(NumberProviderClient.class);
//    @Test
//    @DisplayName("Should return lists of won numbers which contain hit 6 numbers")
//    void should_return_list_of_winning_number() {
//        //Given
//        Clock clock = Clock.fixed(LocalDateTime.of(2022, 8, 15, 12, 0, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.systemDefault());
//        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().buildModuleForTest(numberReceiverFacade, numbersGeneratorFacade, clock, new InputRepositoryTest());
//        LocalDateTime dateTime = LocalDateTime.of(2022, 8, 13, 12, 0, 0);
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
//        List<NumberReceiverResultDto> dtoList = List.of(NumberReceiverMapper.toDto(ValidateMessage.CORRECT_MESSAGE, Optional.of(UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8e")), numbers, Optional.of(dateTime)));
//        given(numberReceiverFacade.outputDrawTime(sampleUserUuid())).willReturn(constants.dateOfDraw);
//        given(numberReceiverFacade.retrieveNumbersForDate(dateTime)).willReturn(constants.resultsList);
//        given(numbersGeneratorFacade.retrieveWonNumbersForDate(dateTime)).willReturn(constants.winningNumbers);
//
//        //When
//        CheckerDto result = resultCheckerFacade.checkWinners(sampleUserUuid());
//
//        //Then
//        Map<UUID, Integer> expected = new HashMap<>();
////        UUID uuid = UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8e");
//        expected.put(sampleUserUuid(), 6);
//        assertThat(result).isEqualTo(expected);
//    }

    @Test
    @DisplayName("Should return lists of won numbers which contain hit six numbers and five numbers")
    void should_return_list_of_with_two_winning_numbers() throws JsonProcessingException {
        //Given
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 8, 15, 12, 0, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.systemDefault());
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().buildModuleForTest(numberReceiverFacade, new InputRepositoryTest(), numberProvider);
        LocalDateTime dateTime = constants.dateOfDraw;
        given(numberReceiverFacade.retrieveNumbersForDate(dateTime)).willReturn(constants.resultsList);
        given(numberReceiverFacade.outputDrawTime(constants.uuid)).willReturn(Optional.of(dateTime));
        given(numberProvider.getWinningNumbers(dateTime)).willReturn(constants.numbers);


        //When
        Map<UUID, Integer> expectedMap = new HashMap<>();
        expectedMap.put(constants.uuid, 6);
        expectedMap.put(constants.uuid1, 5);
        CheckerDto result = resultCheckerFacade.checkWinners(constants.uuid);
        CheckerDto expected = new CheckerDto(expectedMap, dateTime);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should return empty map when invalid uuid provided")
    void should_empty_map_when_invalid_uuid() throws JsonProcessingException {
        //Given
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 8, 15, 12, 0, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.systemDefault());
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().buildModuleForTest(numberReceiverFacade, new InputRepositoryTest(), numberProvider);
        LocalDateTime dateTime = constants.dateOfDraw;
        given(numberReceiverFacade.retrieveNumbersForDate(dateTime)).willReturn(constants.resultsList);
        given(numberReceiverFacade.outputDrawTime(constants.uuid2)).willReturn(Optional.of(dateTime));
        given(numberProvider.getWinningNumbers(dateTime)).willReturn(constants.numbers);


        //When
        Map<UUID, Integer> expectedMap = new HashMap<>();
        CheckerDto result = resultCheckerFacade.checkWinners(constants.uuid2);
        CheckerDto expected = new CheckerDto(expectedMap, LocalDateTime.MAX);

        //Then
        assertThat(result).isEqualTo(expected);

        //Then
        assertThat(result.map()).isEmpty();
    }
}

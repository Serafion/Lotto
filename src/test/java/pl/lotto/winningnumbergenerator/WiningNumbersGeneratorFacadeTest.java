package pl.lotto.winningnumbergenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.MutableClock;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersRepository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static pl.lotto.winningnumbergenerator.TestConstants.*;

class WiningNumbersGeneratorFacadeTest {


    private final WinningNumbersRepository repository = new NumberRepositoryForTest();
    private final Clock clock = Clock.fixed(LocalDateTime.of(2022, 8, 14, 12, 0, 0).atZone(java.time.ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    private final NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);

    @Test
    @DisplayName("should return six random numbers when valid date")
    void shouldReturnDrawNumbersWhenValidDate() {
        //Given
        WiningNumbersGeneratorFacade facade = new WiningNumberGeneratorConfiguration().buildModuleForTests(clock, repository, numberReceiverFacade);
        LocalDateTime dateOfDraw = VALID_DATE_OF_DRAW_WHICH_WAS_DRAWN_EARLIER;

        //When
        NumberReceiverResultDto numbersGenerated = facade.retrieveWonNumbersForDate(dateOfDraw);

        //Then
        assertThat(numbersGenerated.userNumbers().size()).isEqualTo(6);
        assertThat(numbersGenerated).isEqualTo(repository.retrieveArchivalDraw(dateOfDraw));
    }

    @Test
    @DisplayName("Should return zero numbers when invalid date provided")
    void shouldReturnZeroDrawNumbersWhenInvalidDate() {
        //Given
        WiningNumbersGeneratorFacade facade = new WiningNumberGeneratorConfiguration().buildModuleForTests(clock, repository, numberReceiverFacade);

        //When
        NumberReceiverResultDto numbersGenerated = facade.retrieveWonNumbersForDate(INVALID_DATE_OF_DRAW);

        //Then
        assertThat(numbersGenerated.userNumbers().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should return six random numbers when going day of draw and return after date of draw")
    void shouldPerformADrawIfDateWasOneDayBeforeDayOfDrawAndProvideNumbersAfterDrawDate() {
        //Given
        //Clock.instant to mock
        given(numberReceiverFacade.outputDrawTime()).willReturn(VALID_DATE_OF_DRAW_TO_BE_DRAWN);
        MutableClock clock1 = new MutableClock(ZonedDateTime.of(INVALID_DATE_OF_DRAW, ZoneId.systemDefault()));
        WiningNumbersGeneratorFacade facade = new WiningNumberGeneratorConfiguration().buildModuleForTests(clock1, repository, numberReceiverFacade);

        //When
        facade.retrieveWonNumbersForDate(VALID_DATE_OF_DRAW_TO_BE_DRAWN);
        clock1.addDays(6);
        NumberReceiverResultDto numbersGenerated = facade.retrieveWonNumbersForDate(VALID_DATE_OF_DRAW_TO_BE_DRAWN);

        //Then
        assertThat(numbersGenerated.userNumbers().size()).isEqualTo(6);
    }
    @Test
    @DisplayName("Should return zero numbers when asked for draw numbers from future")
    void shouldReturnZeroDrawNumbersDateAfterCurrentDate() {
        //Given
        given(numberReceiverFacade.outputDrawTime()).willReturn(VALID_DATE_OF_DRAW_TO_BE_DRAWN);
        MutableClock clock1 = new MutableClock(ZonedDateTime.of(INVALID_DATE_OF_DRAW, ZoneId.systemDefault()));
        WiningNumbersGeneratorFacade facade = new WiningNumberGeneratorConfiguration().buildModuleForTests(clock1, repository, numberReceiverFacade);

        //When
        NumberReceiverResultDto numbersGenerated = facade.retrieveWonNumbersForDate(VALID_DATE_OF_DRAW_TO_BE_DRAWN);

        //Then
        assertThat(numbersGenerated.userNumbers().size()).isEqualTo(0);
    }
}

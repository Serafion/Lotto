package pl.lotto.winningnumbergenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersRepository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.lotto.numberreceiver.util.Constants.*;

class WiningNumbersGeneratorFacadeTest {

    @Test
    @DisplayName("should return six random numbers when valid date")
    void shouldReturnDrawNumbersWhenValidDate() {
        //Given
        WinningNumbersRepository repository = new NumberRepositoryForTest();
        Clock clock = Clock.fixed(LocalDateTime.of(2022,8,14,12,0,0).atZone(java.time.ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        WiningNumbersGeneratorFacade facade = new WiningNumberGeneratorConfiguration().buildModuleForTests(clock,repository);
        LocalDateTime dateOfDraw = LocalDateTime.of(2022, 8, 13, HOUR_OF_DRAW, MINUTE_OF_DRAW
                ,SECOND_OF_DRAW);

        //When
        List<Integer> numbersGenerated = facade.retrieveWonNumbersForDate(dateOfDraw);

        //Then
        assertThat(numbersGenerated.size()).isEqualTo(6);
        assertThat(numbersGenerated).isEqualTo(repository.retrieveArchivalDraw(dateOfDraw));
    }

    @Test
    @DisplayName("Should return zero numbers when invalid date provided")
    void shouldReturnZeroDrawNumbersWhenInvalidDate() {
        //Given
        WinningNumbersRepository repository = new NumberRepositoryForTest();
        Clock clock = Clock.fixed(LocalDateTime.of(2022,8,9,12,0,0).atZone(java.time.ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        WiningNumbersGeneratorFacade facade = new WiningNumberGeneratorConfiguration().buildModuleForTests(clock,repository);

        LocalDateTime dateOfDraw = LocalDateTime.of(2022, 8, 5, HOUR_OF_DRAW, MINUTE_OF_DRAW
                ,SECOND_OF_DRAW);

        //When
        List<Integer> numbersGenerated = facade.retrieveWonNumbersForDate(dateOfDraw);

        //Then
        assertThat(numbersGenerated.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should return six random numbers when going day of draw and return after date of draw")
    void shouldPerformADrawIfDateWasOneDayBeforeDayOfDrawAndProvideNumbersAfterDrawDate() {
        //Given
        WinningNumbersRepository repository = new NumberRepositoryForTest();
        Clock clock = Clock.fixed(LocalDateTime.of(2022,9,9,12,0,0).atZone(java.time.ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        WiningNumbersGeneratorFacade facade = new WiningNumberGeneratorConfiguration().buildModuleForTests(clock,repository);



        LocalDateTime dateOfDraw = LocalDateTime.of(2022, 9, 3, HOUR_OF_DRAW, MINUTE_OF_DRAW
                ,SECOND_OF_DRAW);

        //When
        List<Integer> numbersGenerated = facade.retrieveWonNumbersForDate(dateOfDraw);

        //Then
        assertThat(numbersGenerated.size()).isEqualTo(6);
    }
    @Test
    @DisplayName("Should return zero numbers when asked for draw numbers from future")
    void shouldReturnZeroDrawNumbersDateAfterCurrentDate() {
        //Given
        WinningNumbersRepository repository = new NumberRepositoryForTest();
        Clock clock = Clock.fixed(LocalDateTime.of(2022,8,9,12,0,0).atZone(java.time.ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        WiningNumbersGeneratorFacade facade = new WiningNumberGeneratorConfiguration().buildModuleForTests(clock,repository);

        LocalDateTime dateOfDraw = LocalDateTime.of(2022, 8, 13, HOUR_OF_DRAW, MINUTE_OF_DRAW
                ,SECOND_OF_DRAW);

        //When
        List<Integer> numbersGenerated = facade.retrieveWonNumbersForDate(dateOfDraw);

        //Then
        assertThat(numbersGenerated.size()).isEqualTo(0);
    }
}

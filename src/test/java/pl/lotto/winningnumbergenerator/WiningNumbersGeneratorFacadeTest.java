package pl.lotto.winningnumbergenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.lotto.numberreceiver.util.Constants.*;

class WiningNumbersGeneratorFacadeTest {

    @Test
    @DisplayName("should return six random numbers when valid date")
    void shouldReturnDrawNumbersWhenValidDate() {
        //Given
        NumberProvidable generator = new WiningNumbersGeneratorFacade();
        LocalDateTime dateOfDraw = LocalDateTime.of(2022, 8, 6, HOUR_OF_DRAW, MINUTE_OF_DRAW
                ,SECOND_OF_DRAW);

        //When
        List<Integer> numbersGenerated = generator.retrieveWonNumbersForDate(dateOfDraw);

        //Then
        assertThat(numbersGenerated.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("Should return zero numbers when invalid date provided")
    void shouldReturnZeroDrawNumbersWhenInvalidDate() {
        //Given
        NumberProvidable generator = new WiningNumbersGeneratorFacade();
        LocalDateTime dateOfDraw = LocalDateTime.of(2022, 8, 5, HOUR_OF_DRAW, MINUTE_OF_DRAW
                ,SECOND_OF_DRAW);

        //When
        List<Integer> numbersGenerated = generator.retrieveWonNumbersForDate(dateOfDraw);

        //Then
        assertThat(numbersGenerated.size()).isEqualTo(0);
    }
}

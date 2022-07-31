package pl.lotto.numberreceiver.dategenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DateGeneratorTest {
    @Test
    @DisplayName("Should show next Saturday as date of draw")
    void should_display_next_saturday_after_today(){
        //given
        DateGenerator dateGenerator = new DateGenerator();

        //when
        LocalDateTime date_of_draw = dateGenerator.retrieveNextDrawDate();

        //then
        assertThat(date_of_draw.getDayOfWeek()).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(date_of_draw).isAfter(LocalDateTime.now());
    }

}
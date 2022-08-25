package pl.lotto.numberreceiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

class DateGeneratorTest {
    @Test
    @DisplayName("Should show next Saturday as date of draw")
    void should_display_next_saturday_after_today() {
        //given
        Clock clock = Clock.fixed(Instant.ofEpochMilli(1659772800000L), ZoneId.systemDefault());
        DateGenerator dateGenerator = new DateGenerator(clock);

        //when
        LocalDateTime date_of_draw = dateGenerator.retrieveNextDrawDate();

        //then
        assertThat(date_of_draw.getDayOfWeek()).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(date_of_draw).isEqualTo(LocalDateTime.of(2022,8,6,12,0,0));
    }

}
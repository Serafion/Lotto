package pl.lotto.numberreceiver.dategenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

class DateGeneratorTest {
    @Test
    @DisplayName("Should show next Saturday as date of draw")
    void should_display_next_saturday_after_today(){
        //given
        Clock clock = Clock.fixed(Instant.ofEpochMilli(1659772800000L),ZoneId.systemDefault());
        DateGenerator dateGenerator = new DateGenerator(clock);

        //when
        Long date_of_draw = dateGenerator.retrieveNextDrawDate();

        //then
        assertThat(Instant.ofEpochMilli(date_of_draw).atZone(ZoneId.of(clock.getZone().getId())).getDayOfWeek()).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(date_of_draw).isEqualTo(1659780000000L);
    }

}
package pl.lotto;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


public class TestClock extends Clock {

    List<Instant> instants = List.of(
            LocalDateTime.of(2022, 02, 12, 10, 10).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 12, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 12, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 12, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 12, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 12, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 12, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 14, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 14, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 14, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 14, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 14, 10, 12).atZone(ZoneId.systemDefault()).toInstant(),
            LocalDateTime.of(2022, 02, 14, 10, 12).atZone(ZoneId.systemDefault()).toInstant());

    Integer instantNo = 0;


    public TestClock() {
    }

    @Override
    public ZoneId getZone() {
        return ZoneId.systemDefault();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return new TestClock();
    }

    @Override
    public Instant instant() {
        Instant instant = instants.get(instantNo);
//        System.out.println(instantNo);
        instantNo++;
        return instant;
    }


}

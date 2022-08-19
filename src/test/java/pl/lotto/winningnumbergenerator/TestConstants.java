package pl.lotto.winningnumbergenerator;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.*;

import static pl.lotto.numberreceiver.util.Constants.*;

public class TestConstants {
    public static final LocalDateTime VALID_DATE_OF_DRAW_WHICH_WAS_DRAWN_EARLIER = LocalDateTime.of(2022, 8, 13, HOUR_OF_DRAW, MINUTE_OF_DRAW
            , SECOND_OF_DRAW);
    public static final LocalDateTime VALID_DATE_OF_DRAW_TO_BE_DRAWN = LocalDateTime.of(2022, 8, 20, HOUR_OF_DRAW, MINUTE_OF_DRAW
            , SECOND_OF_DRAW);
    public static final LocalDateTime INVALID_DATE_OF_DRAW = LocalDateTime.of(2022, 8, 15, HOUR_OF_DRAW, MINUTE_OF_DRAW
            , SECOND_OF_DRAW);
    private final HashMap<LocalDateTime, NumberReceiverResultDto> map;
    private final List<LocalDateTime> drawDates;

    public TestConstants() {
        this.map = new HashMap<>();
//        LocalDateTime dateOfDraw = LocalDateTime.of(2022, 8, 13, HOUR_OF_DRAW, MINUTE_OF_DRAW
//                ,SECOND_OF_DRAW);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        map.put(VALID_DATE_OF_DRAW_WHICH_WAS_DRAWN_EARLIER, new NumberReceiverResultDto("", Optional.empty(), list, Optional.of(VALID_DATE_OF_DRAW_WHICH_WAS_DRAWN_EARLIER)));
        drawDates = new ArrayList<>();
        drawDates.add(VALID_DATE_OF_DRAW_WHICH_WAS_DRAWN_EARLIER);

    }

    public HashMap<LocalDateTime, NumberReceiverResultDto> getMap() {
        return map;
    }

    public List<LocalDateTime> getDrawDates() {
        return drawDates;
    }
}

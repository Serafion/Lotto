package pl.lotto.resultchecker;

import pl.lotto.MutableClock;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.winningnumbergenerator.winningnumbersdto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Constants {
    LocalDateTime clockDate = LocalDateTime.of(2022, 8, 12, 12, 0, 0);

    MutableClock clock = new MutableClock(ZonedDateTime.of(clockDate, ZoneId.systemDefault()));
    List<NumberReceiverResultDto> resultsList;
    UUID uuid = UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8e");
    UUID uuid1 = UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8f");
    UUID uuid2 = UUID.fromString("571fc5dd-b0ee-4587-90db-34c764273a8f");
    UUID uuid3 = UUID.fromString("571fc5dd-b0ee-4597-90db-34c764273a8f");

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 7);
    List<Integer> numbers2 = Arrays.asList(8, 9, 10, 11, 12, 7);
    List<Integer> numbers3 = Arrays.asList(8, 9, 10, 11, 12, 77);
    LocalDateTime dateOfDraw = LocalDateTime.of(2022, 8, 13, 12, 0, 0);
    LocalDateTime dateOfDrawAfterDraw = LocalDateTime.of(2022, 8, 13, 12, 0, 0);
    WinningNumbersDto winningNumbers = new WinningNumbersDto(numbers, dateOfDraw);

    public Constants() {
        List<NumberReceiverResultDto> dtoList = Arrays.asList(
                new NumberReceiverResultDto("CORRECT_MESSAGE", Optional.of(uuid), numbers, Optional.of(dateOfDraw)),
                new NumberReceiverResultDto("CORRECT_MESSAGE", Optional.of(uuid1), numbers1, Optional.of(dateOfDraw)),
                new NumberReceiverResultDto("CORRECT_MESSAGE", Optional.of(uuid2), numbers2, Optional.of(dateOfDraw)),
                new NumberReceiverResultDto("CORRECT_MESSAGE", Optional.of(uuid3), numbers3, Optional.of(dateOfDraw)));

        this.resultsList = dtoList;
    }

    public List<NumberReceiverResultDto> getResultsList() {
        return resultsList;
    }
}

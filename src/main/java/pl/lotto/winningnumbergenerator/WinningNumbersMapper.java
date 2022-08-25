package pl.lotto.winningnumbergenerator;

import pl.lotto.winningnumbergenerator.winningnumbersdto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.List;

class WinningNumbersMapper {
    static WinningNumbersDto toDto(List<Integer> list, LocalDateTime dateTime) {
        return new WinningNumbersDto(list, dateTime);
    }
}

package pl.lotto.winningnumbergenerator.winningnumbersdto;

import java.time.LocalDateTime;
import java.util.List;

public record WinningNumbersDto(List<Integer> winningNumbers,
                                LocalDateTime drawDate) {
}

package pl.lotto.resultchecker.resultcalculator;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Calculatable {
    Map<UUID, WonNumbersCount> calculateResults(List<NumberReceiverResultDto> inputs, NumberReceiverResultDto winningNumbers);
}

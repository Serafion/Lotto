package pl.lotto.resultchecker.dataretriever;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.List;

public interface Retrievable {
    List<Integer> fetchWonNumbers();

    List<NumberReceiverResultDto> fetchNewTickets();

    LocalDateTime calculateLastDrawDate();
}

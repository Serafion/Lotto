package pl.lotto.resultchecker.repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

public interface Updatable {

    boolean dataContainsTicket(UUID uuid);

    Map<UUID, WonNumbersCount> fetchResults();

    boolean serviceContainsDrawDate(LocalDateTime drawDate);
}

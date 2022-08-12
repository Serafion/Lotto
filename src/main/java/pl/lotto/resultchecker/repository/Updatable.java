package pl.lotto.resultchecker.repository;

import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public interface Updatable {
   boolean DataContainsTicket(UUID uuid);

   Map<UUID, WonNumbersCount> fetchResults();

   boolean serviceContainsDrawDate(LocalDateTime drawDate);
}

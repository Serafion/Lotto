package pl.lotto.resultchecker.repository;

import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public interface InputService {
   boolean uuidPresent(UUID uuid);

   Map<UUID, WonNumbersCount> fetchResults(UUID uuid);

   boolean serviceContainsDrawDate(LocalDateTime drawDate);

   void addDate(LocalDateTime dateTime);

   LocalDateTime getLastPasedDrawDate();
}

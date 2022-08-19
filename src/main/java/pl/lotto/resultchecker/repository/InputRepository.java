package pl.lotto.resultchecker.repository;

import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface InputRepository {
    Set<LocalDateTime> retrieveDrawDates();

    Map<UUID, WonNumbersCount> retrieveData(UUID uuid);

    Map<UUID, WonNumbersCount> retrieveAllData();

    void updateDrawDates(LocalDateTime drawDate);

    void updateData(Map<UUID, WonNumbersCount> data);
}

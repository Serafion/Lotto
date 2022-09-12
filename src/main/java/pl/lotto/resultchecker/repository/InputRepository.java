package pl.lotto.resultchecker.repository;

import pl.lotto.resultchecker.checkerdto.CheckerDto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public interface InputRepository {
    Set<LocalDateTime> retrieveDrawDates();

    CheckerDto retrieveData(UUID uuid);

    LocalDateTime drawDate(UUID uuid);

    boolean containsWinningNumberForDrawDate(LocalDateTime drawDate);

    void updateDrawDates(LocalDateTime drawDate);

    void updateData(Map<UUID, Integer> data, LocalDateTime dateTime);

    boolean dataContainsTicket(UUID uuid);
}

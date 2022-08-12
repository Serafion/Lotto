package pl.lotto.resultchecker.repository;

import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NumbersService implements Updatable {
    private final InputRepository inputRepository;
    private final Map<UUID, WonNumbersCount> map;
    private final Set<LocalDateTime> dates;

    public NumbersService(InputRepository inputRepository) {
        this.inputRepository = inputRepository;
        this.map = inputRepository.retrieveData();
        this.dates = inputRepository.retrieveDrawDates();
    }

    @Override
    public boolean DataContainsTicket(UUID uuid) {
        return map.containsKey(uuid);
    }

    @Override
    public boolean serviceContainsDrawDate(LocalDateTime drawDate) {
        return dates.contains(drawDate);
    }

    @Override
    public Map<UUID, WonNumbersCount> fetchResults() {
        return map;
    }
}

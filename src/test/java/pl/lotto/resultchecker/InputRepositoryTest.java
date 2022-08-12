package pl.lotto.resultchecker;

import pl.lotto.resultchecker.repository.InputRepository;
import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.time.LocalDateTime;
import java.util.*;

public class InputRepositoryTest implements InputRepository {
    @Override
    public Set<LocalDateTime> retrieveDrawDates() {
        return new HashSet<>();
    }

    @Override
    public Map<UUID, WonNumbersCount> retrieveData() {
        return new HashMap<>();
    }

    @Override
    public void updateDrawDates(LocalDateTime drawDate) {

    }

    @Override
    public void updateData(Map<UUID, WonNumbersCount> data) {

    }
}

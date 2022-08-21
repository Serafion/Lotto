package pl.lotto.resultannouncer;

import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Constants {
    UUID uuid = UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8e");
    UUID uuid1 = UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8f");
    UUID uuid2 = UUID.fromString("571fc5dd-b0ee-4587-90db-34c764273a8f");
    Map<UUID, WonNumbersCount> expected = new HashMap<>();

    public Constants() {
        expected.put(uuid, WonNumbersCount.SIX_NUMBERS_HIT);
        expected.put(uuid1, WonNumbersCount.FIVE_NUMBERS_HIT);
    }
}

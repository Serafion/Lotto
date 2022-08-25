package pl.lotto.resultannouncer;

import pl.lotto.resultchecker.checkerdto.CheckerDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConstantsTest {
    UUID uuid = UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8e");
    UUID uuid1 = UUID.fromString("571fc5dd-b0ee-4557-90db-34c764273a8f");
    UUID uuid2 = UUID.fromString("571fc5dd-b0ee-4587-90db-34c764273a8f");
    LocalDateTime dateTime = LocalDateTime.now();
    CheckerDto checkerDto;


    public ConstantsTest() {
        Map<UUID, Integer> expected = new HashMap<>();
        expected.put(uuid, 6);
        expected.put(uuid1, 5);
        checkerDto = new CheckerDto(expected, dateTime);
    }
}

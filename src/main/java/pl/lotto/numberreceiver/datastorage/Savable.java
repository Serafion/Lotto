package pl.lotto.numberreceiver.datastorage;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Savable {
    void save(Map<LocalDateTime, List<UserInput>> map);
}

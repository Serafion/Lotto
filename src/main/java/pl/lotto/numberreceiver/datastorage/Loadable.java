package pl.lotto.numberreceiver.datastorage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface Loadable {
    Map<LocalDateTime, List<UserInput>> fetch();
}

package pl.lotto.numberreceiver.datastorage;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface Loadable {
    HashMap<LocalDateTime, List<UserInput>> loadDatabase();
}

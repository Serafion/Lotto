package pl.lotto.numberreceiver;

import java.util.Map;
import pl.lotto.numberreceiver.datastorage.Loadable;
import pl.lotto.numberreceiver.datastorage.UserInput;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class LoaderTest implements Loadable {
    @Override
    public Map<LocalDateTime, List<UserInput>> fetch() {
        return new HashMap<>();
    }
}

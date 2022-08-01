package pl.lotto.numberreceiver;

import java.util.Map;
import pl.lotto.numberreceiver.datastorage.Savable;
import pl.lotto.numberreceiver.datastorage.UserInput;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class SaverTest implements Savable {
    @Override
    public void save(Map<LocalDateTime, List<UserInput>> map) {

    }
}

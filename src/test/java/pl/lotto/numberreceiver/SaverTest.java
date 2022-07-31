package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.datastorage.Savable;
import pl.lotto.numberreceiver.datastorage.UserInput;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class SaverTest implements Savable {
    @Override
    public void saveToFile(HashMap<LocalDateTime, List<UserInput>> map) {

    }
}

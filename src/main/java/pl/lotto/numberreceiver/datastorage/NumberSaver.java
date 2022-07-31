package pl.lotto.numberreceiver.datastorage;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

class NumberSaver implements Serializable, Savable {


    public NumberSaver() {
    }

    @Override
    public void saveToFile(HashMap<LocalDateTime, List<UserInput>> map) {
        try (ObjectOutputStream inputedNumbers = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("userinput.dat")))) {
            inputedNumbers.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

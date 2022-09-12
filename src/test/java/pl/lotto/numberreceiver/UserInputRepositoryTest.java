package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UserInputRepositoryTest implements UserInputRepository {


    HashMap<LocalDateTime,List<UserInput>> records = new HashMap<>();
    List<UserInput> validInputs = new ArrayList<>();
    @Override
    public UserInput save(UserInput userInput) {
        validInputs.add(userInput);
        records.put(userInput.date(), validInputs);
        return validInputs.get(validInputs.size() - 1);
    }

    @Override
    public List<UserInput> findAllByDate(LocalDateTime date) {
        return records.get(date);
    }


    @Override
    public LocalDateTime getDateOfDraw(UUID uuid) {
        return null;
    }

}

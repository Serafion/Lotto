package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.repository.UserInput;
import pl.lotto.numberreceiver.repository.UserInputRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserInputRepositoryTest implements UserInputRepository {


    HashMap<LocalDateTime,List<UserInput>> records = new HashMap<>();
    List<UserInput> validInputs = new ArrayList<>();
    @Override
    public UserInput save(UserInput userInput) {
        validInputs.add(userInput);
        records.put(userInput.drawDate(),validInputs);
        return validInputs.get(validInputs.size()-1);
    }

    @Override
    public List<UserInput> findAllByDate(LocalDateTime date) {
        return records.get(date);
    }

}

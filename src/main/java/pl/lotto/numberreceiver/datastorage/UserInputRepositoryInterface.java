package pl.lotto.numberreceiver.datastorage;

import java.time.LocalDateTime;
import java.util.List;

public interface UserInputRepositoryInterface {

    UserInput save(UserInput userInput);

    List<UserInput> findAllByDate(LocalDateTime date);

}

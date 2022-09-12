package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface UserInputRepository {


    UserInput save(UserInput userInput);


    List<UserInput> findAllByDate(LocalDateTime date);


    LocalDateTime getDateOfDraw(UUID uuid);

}

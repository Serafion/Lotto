package pl.lotto.numberreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class MongoRepository implements UserInputRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public UserInput save(UserInput userInput) {
        mongoTemplate.save(userInput);
        return userInput;
    }

    @Override
    public List<UserInput> findAllByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public LocalDateTime getDateOfDraw(UUID uuid) {
        return null;
    }
}

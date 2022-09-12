package pl.lotto.numberreceiver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface MongoInterface extends MongoRepository<UserInput, UUID> {
}

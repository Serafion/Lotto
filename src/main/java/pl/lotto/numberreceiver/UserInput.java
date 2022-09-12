package pl.lotto.numberreceiver;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Document
record UserInput(String message, List<Integer> numbersFromUser, @MongoId UUID uniqueLotteryID,
                 LocalDateTime date) implements Serializable {
}

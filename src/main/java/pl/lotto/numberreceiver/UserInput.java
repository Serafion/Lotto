package pl.lotto.numberreceiver;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document
public record UserInput(
        String message,
        List<Integer> numbersFromUser,
        @MongoId UUID uniqueLotteryID,
        LocalDateTime date)
        implements Serializable {
}

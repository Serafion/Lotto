package pl.lotto.numberreceiver.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Document
public record UserInput(
        String message,
        List<Integer> numbersFromUser,
        @MongoId UUID uniqueLotteryID,
        LocalDateTime date)
        implements Serializable {
}

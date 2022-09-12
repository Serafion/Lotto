package pl.lotto.resultchecker.checkerdto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Document
public record CheckerDto(Map<UUID, Integer> map,
                         @MongoId LocalDateTime dateTime) {
}

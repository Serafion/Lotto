package pl.lotto.resultchecker.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public record CheckerRepoEntity(@MongoId UUID uuid, LocalDateTime dateTime) {
}

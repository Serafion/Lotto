package pl.lotto.resultannouncer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MongoAnnouncer extends MongoRepository<ResultTicket, UUID> {
}

package pl.lotto.resultchecker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DrawDateRepository extends MongoRepository<CheckerRepoEntity, UUID> {
}

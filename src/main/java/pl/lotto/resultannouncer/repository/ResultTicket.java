package pl.lotto.resultannouncer.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class ResultTicket {
    @MongoId
    UUID uuid;

    Integer wonNumbers;

    LocalDateTime date;

    public ResultTicket() {
    }

    public ResultTicket(UUID uuid, Integer wonNumbers, LocalDateTime date) {
        this.uuid = uuid;
        this.wonNumbers = wonNumbers;
        this.date = date;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getWonNumbers() {
        return wonNumbers;
    }

    public void setWonNumbers(Integer wonNumbers) {
        this.wonNumbers = wonNumbers;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

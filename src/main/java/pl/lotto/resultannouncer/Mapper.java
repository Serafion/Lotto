package pl.lotto.resultannouncer;

import pl.lotto.resultchecker.checkerdto.CheckerDto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

class Mapper {
    static Map<UUID, Integer> mappingToMap(CheckerDto dto) {
        return dto.map();
    }

    static LocalDateTime mapDate(CheckerDto dto) {
        return dto.dateTime();
    }
}

package pl.lotto.resultchecker.checkerdto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record CheckerDto(Map<UUID, Integer> map,
                         LocalDateTime dateTime) {
}

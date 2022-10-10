package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.resultchecker.checkerdto.CheckerDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


class ResultCheckerDtoMapper {
    static CheckerDto mapToCheckerDto(Map<UUID, Integer> map, LocalDateTime drawDate) {
        return new CheckerDto(map, drawDate);
    }

    static Map<UUID, List<Integer>> mapReceiverDtoToMap(List<NumberReceiverResultDto> results) {
        Map<UUID, List<Integer>> map = new HashMap<>();
        for (NumberReceiverResultDto dto : results) {
            map.put(dto.uniqueLotteryId().get(), dto.userNumbers());
        }
        return map;
    }


}

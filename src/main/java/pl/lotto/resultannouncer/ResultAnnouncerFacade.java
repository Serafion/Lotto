package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.repository.ResultTicket;
import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.checkerdto.CheckerDto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static pl.lotto.resultannouncer.Constants.INVALID_TICKET_PROVIDED;

public class ResultAnnouncerFacade {

    private final ResultCheckerFacade resultChecker;
    private final WinnerRepository winnerRepository;

    public ResultAnnouncerFacade(ResultCheckerFacade resultChecker, WinnerRepository winnerRepository) {
        this.resultChecker = resultChecker;
        this.winnerRepository = winnerRepository;
    }

    public String checkWinner(UUID uuid) {
        if (winnerRepository.existsById(uuid)) {
            return Messenger.fetchMessage(
                    winnerRepository.findById(uuid).get().getWonNumbers());
        }
        CheckerDto dto = resultChecker.checkWinners(uuid);
        if (Mapper.mapDate(dto).equals(LocalDateTime.MIN)) {
            return INVALID_TICKET_PROVIDED;
        }
        LocalDateTime drawDate = Mapper.mapDate(dto);
        Map<UUID, Integer> resultMap = Mapper.mappingToMap(dto);
        for (UUID uuid1 : resultMap.keySet()) {
            winnerRepository.save(new ResultTicket(uuid1, resultMap.get(uuid1), drawDate));
        }
        return Messenger.fetchMessage(checkTicketForWinningNumbers(uuid));
    }

    private Integer checkTicketForWinningNumbers(UUID uuid) {
        return winnerRepository.existsById(uuid) ? winnerRepository.findById(uuid).get().getWonNumbers() : Constants.NO_HIT_NUMBERS;
    }
}

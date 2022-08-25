package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.checkerdto.CheckerDto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class ResultAnnouncerFacade {

    private final ResultCheckerFacade resultChecker;
    private final WinnerRepository winnerRepository;
    private UUID uuid;
    private Map<UUID, Integer> wonTicketWithHitNumbers;

    public ResultAnnouncerFacade(ResultCheckerFacade resultChecker, WinnerRepository winnerRepository) {
        this.resultChecker = resultChecker;
        this.winnerRepository = winnerRepository;
    }

    public String checkWinner(UUID uuid) {
        this.uuid = uuid;
        if (winnerRepository.containsWinningNumbers(uuid)) {
            return Messenger.fetchMessage(winnerRepository.getDataFrom(uuid));
        }
        CheckerDto dto = resultChecker.checkWinners(uuid);
        wonTicketWithHitNumbers = Mapper.mappingToMap(dto);
        LocalDateTime drawDate = Mapper.mapDate(dto);
        winnerRepository.saveNewDataToRepository(wonTicketWithHitNumbers, drawDate);
        return Messenger.fetchMessage(checkTicketForWinningNumbers());
    }

    private Integer checkTicketForWinningNumbers() {
        return wonTicketWithHitNumbers.containsKey(uuid) ? wonTicketWithHitNumbers.get(uuid) : Constants.NO_HIT_NUMBERS;
    }
}

package pl.lotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.RecordMapper;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.repository.UserInputRepository;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Profile("test")
public class Bootstrap implements CommandLineRunner {

    public UserInputRepository userInputRepository;
    public WinnerRepository winnerRepository;
    public ResultCheckerRepository resultCheckerRepository;
    public NumberReceiverFacade numberReceiverFacade;
    public ResultAnnouncerFacade resultAnnouncerFacade;
    public NumberReceiverResultDto winningTicket;


    @Autowired
    public Bootstrap(UserInputRepository userInputRepository,
                     WinnerRepository winnerRepository,
                     ResultCheckerRepository resultCheckerRepository,
                     NumberReceiverFacade numberReceiverFacade,
                     ResultAnnouncerFacade resultAnnouncerFacade) {
        this.userInputRepository = userInputRepository;
        this.winnerRepository = winnerRepository;
        this.resultCheckerRepository = resultCheckerRepository;
        this.numberReceiverFacade = numberReceiverFacade;
        this.resultAnnouncerFacade = resultAnnouncerFacade;
    }

    @Override
    public void run(String... args) throws Exception {
        NumberReceiverResultDto ticket1 = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        NumberReceiverResultDto ticket2 = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 7));
        NumberReceiverResultDto ticket3 = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 8));
        NumberReceiverResultDto ticket4 = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 9));
        NumberReceiverResultDto ticket5 = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 10));
        NumberReceiverResultDto ticket6 = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6));

        System.out.println(userInputRepository.findAll().size());

        userInputRepository.delete(RecordMapper.fromDto(ticket1));
        LocalDateTime dateTime = ticket1.dateOfDraw().get().minusDays(7);
        winningTicket = ticket1;
        NumberReceiverResultDto ArchivalResult = new NumberReceiverResultDto("", ticket1.uniqueLotteryId(), ticket1.userNumbers(), Optional.of(dateTime));
        userInputRepository.save(RecordMapper.fromDto(ArchivalResult));
        System.out.println(resultAnnouncerFacade.checkWinner(ticket1.uniqueLotteryId().get()));
    }
}

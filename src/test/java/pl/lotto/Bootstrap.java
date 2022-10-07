package pl.lotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.RecordMapper;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.repository.UserInputRepository;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultchecker.repository.DrawDateRepository;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersRepository;
import pl.lotto.winningnumbergenerator.winningnumbersdto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class Bootstrap implements CommandLineRunner {

    public UserInputRepository userInputRepository;
    public WinnerRepository winnerRepository;
    public ResultCheckerRepository resultCheckerRepository;
    public DrawDateRepository drawDateRepository;
    public WinningNumbersRepository winningNumbersRepository;
    public NumberReceiverFacade numberReceiverFacade;
    public ResultAnnouncerFacade resultAnnouncerFacade;

    public NumberReceiverResultDto winningTicket;


    @Autowired
    public Bootstrap(UserInputRepository userInputRepository,
                     WinnerRepository winnerRepository,
                     ResultCheckerRepository resultCheckerRepository,
                     DrawDateRepository drawDateRepository,
                     WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade, ResultAnnouncerFacade resultAnnouncerFacade) {
        this.userInputRepository = userInputRepository;
        this.winnerRepository = winnerRepository;
        this.resultCheckerRepository = resultCheckerRepository;
        this.drawDateRepository = drawDateRepository;
        this.winningNumbersRepository = winningNumbersRepository;
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
        winningNumbersRepository.save(new WinningNumbersDto(ArchivalResult.userNumbers(), ArchivalResult.dateOfDraw().get()));
        System.out.println(resultAnnouncerFacade.checkWinner(ticket1.uniqueLotteryId().get()));
        //simple happy path test and it works
//        System.out.println(winnerRepository.findAll().size());
//        System.out.println(resultCheckerRepository.findAll().size());
//        System.out.println(drawDateRepository.findAll().size());
//        System.out.println(winningNumbersRepository.findAll().size());


        for (WinningNumbersDto dto : winningNumbersRepository.findAll()) {
            System.out.println(dto.winningNumbers() + " at date " + dto.drawDate());
        }

    }
}

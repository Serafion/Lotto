package pl.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.winningnumbergenerator.WiningNumbersGeneratorFacade;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LottoApplicationTest {


    @Autowired
    NumberReceiverFacade numberReceiverFacade;
    @Autowired
    ResultAnnouncerFacade resultAnnouncerFacade;
    @Autowired
    WiningNumbersGeneratorFacade winingNumbersGeneratorFacade;
    @Autowired
    Bootstrap bootstrap;
//
//    @Autowired
//    MutableClock clock;

    @BeforeEach
    void setUp() {
//        List<WinningNumbersDto> winningNumbers = bootstrap.winningNumbersRepository.findAll();
////        List<NumberReceiverResultDto> userInputs = numberReceiverFacade.retrieveNumbersForDate();
//        for(WinningNumbersDto winningNumbersDto : winningNumbers){
//            System.out.println(winningNumbersDto.winningNumbers());
//            System.out.println(winningNumbersDto.drawDate());
//        }

    }

    @Test
    void contextLoads() {
    }

    @Test
    void should_return_winning_massage_happy_path() {
        //Given
        NumberReceiverResultDto numberReceiverResultDto = bootstrap.winningTicket;


        //When
        String result = resultAnnouncerFacade.checkWinner(numberReceiverResultDto.uniqueLotteryId().get());

        //Then
        assertThat(result).isEqualTo("You've hit: 6 numbers");

    }

//    @Test
//    void should_return_winning_massage_happy_path_second_test(){
//        //Given
//        List<Integer> list = List.of(1,2,3,4,5,6);
//        NumberReceiverResultDto ticket = numberReceiverFacade.inputNumbers(list);
//        LocalDateTime drawDate = numberReceiverFacade.outputDrawTime(ticket.uniqueLotteryId().get());
//        bootstrap.winningNumbersRepository.save(new WinningNumbersDto(list,drawDate));
//
//
//
//        //When
//        String result = resultAnnouncerFacade.checkWinner(ticket.uniqueLotteryId().get());
//
//        //Then
//        assertThat(result).isEqualTo("You've hit: 6 numbers");
//
//    }

}
package pl.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.winningnumbergenerator.WiningNumbersGeneratorFacade;

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

//    @Test
//    void should_return_winning_massage_happy_path() {
//        //Given
//        NumberReceiverResultDto numberReceiverResultDto = bootstrap.winningTicket;
//
//
//        //When
//        String result = resultAnnouncerFacade.checkWinner(numberReceiverResultDto.uniqueLotteryId().get());
//
//        //Then
//        assertThat(result).isEqualTo("You've hit: 6 numbers");
//
//    }

//    @Test
//    void should_return_winning_massage_happy_path_second_test() throws Exception {
//        //Given
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup().build();
//        // given
//        String path = "/process";
//        InputNumbersRequest inputNumbersRequest= new InputNumbersRequest();
//        inputNumbersRequest.setNumbers("1,2,3,4,5,6");
//        String json = new ObjectMapper().writeValueAsString(inputNumbersRequest);
//        System.out.println(json);
//
//        // when and then
//        mockMvc.perform(post(path)
//                .contentType(APPLICATION_JSON)
//                .content(json)
//                .accept(APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
//
//    }

}
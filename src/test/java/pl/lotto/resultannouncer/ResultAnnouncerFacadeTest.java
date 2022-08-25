package pl.lotto.resultannouncer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lotto.resultchecker.ResultCheckerFacade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ResultAnnouncerFacadeTest {
    private final WinnerRepositoryTest winnerRepository = new WinnerRepositoryTest();
    private final ConstantsTest constants = new ConstantsTest();
    @InjectMocks
    private ResultCheckerFacade resultChecker = mock(ResultCheckerFacade.class);

    @Test
    @DisplayName("Should return that user hit six numbers")
    void should_return_that_user_hit_six_numbers() {
        //Given
        ResultAnnouncerFacade facade = new ResultAnnouncerConfiguration().buildModuleForProduction(resultChecker, winnerRepository);
        given(resultChecker.checkWinners(constants.uuid)).willReturn(constants.checkerDto);

        //When
        String result = facade.checkWinner(constants.uuid);

        //Then
        assertThat(result).isEqualTo("You've hit: 6 numbers");
    }

    @Test
    @DisplayName("Should return that user hit five numbers")
    void should_return_that_user_hit_five_numbers() {
        //Given
        ResultAnnouncerFacade facade = new ResultAnnouncerConfiguration().buildModuleForProduction(resultChecker, winnerRepository);
        given(resultChecker.checkWinners(constants.uuid1)).willReturn(constants.checkerDto);

        //When
        String result = facade.checkWinner(constants.uuid1);

        //Then
        assertThat(result).isEqualTo("You've hit: 5 numbers");
    }

    @Test
    @DisplayName("Should return that user did not won")
    void should_return_that_user_hit_no_numbers() {
        //Given
        ResultCheckerFacade resultChecker = mock(ResultCheckerFacade.class);
        ResultAnnouncerFacade facade = new ResultAnnouncerConfiguration().buildModuleForProduction(resultChecker, winnerRepository);
        given(resultChecker.checkWinners(constants.uuid2)).willReturn(constants.checkerDto);

        //When
        String result = facade.checkWinner(constants.uuid2);

        //Then
        assertThat(result).isEqualTo("sorry it's a loosing game");
    }

}
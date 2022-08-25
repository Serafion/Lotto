package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.checkerdto.CheckerDto;
import pl.lotto.resultchecker.repository.InputRepository;
import pl.lotto.winningnumbergenerator.WiningNumbersGeneratorFacade;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ResultCheckerFacade {

    private final InputRepository inputRepository;
    private final NumberReceiverFacade numberReceiverFacade;
    private final WiningNumbersGeneratorFacade numbersGeneratorFacade;
    private final ResultCalculator calculator;
    private final Clock clock;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, WiningNumbersGeneratorFacade numbersGeneratorFacade, Clock clock, InputRepository inputRepository, ResultCalculator calculator) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numbersGeneratorFacade = numbersGeneratorFacade;
        this.clock = clock;
        this.inputRepository = inputRepository;
        this.calculator = calculator;
    }

    public CheckerDto checkWinners(UUID uuid) {
        LocalDateTime drawDate = numberReceiverFacade.outputDrawTime(uuid);
        if (inputRepository.dataContainsTicket(uuid)) {
            return inputRepository.retrieveData(uuid);
        }
        Map<UUID, List<Integer>> inputs = fetchInputMap(drawDate);
        List<Integer> wonNumbers = fetchWonNumbers(drawDate);
        Map<UUID, Integer> map = calculator.calculateResults(inputs, wonNumbers);
        inputRepository.updateData(map, drawDate);
        return map.containsKey(uuid) ? fetchCheckerDto(map, drawDate) : ResultCheckerDtoMapper.mapToCheckerDto(new HashMap<>(), LocalDateTime.MIN);
    }

    private List<Integer> fetchWonNumbers(LocalDateTime drawDate) {
        return ResultCheckerDtoMapper.mapWinningNumbersList(numbersGeneratorFacade.retrieveWonNumbersForDate(drawDate));
    }

    private Map<UUID, List<Integer>> fetchInputMap(LocalDateTime drawDate) {
        return ResultCheckerDtoMapper.mapReceiverDtoToMap(numberReceiverFacade.retrieveNumbersForDate(drawDate));
    }


    private CheckerDto fetchCheckerDto(Map<UUID, Integer> map, LocalDateTime dateTime) {
        return ResultCheckerDtoMapper.mapToCheckerDto(map, dateTime);
    }
}

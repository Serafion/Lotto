package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.checkerdto.CheckerDto;
import pl.lotto.resultchecker.repository.CheckerRepoEntity;
import pl.lotto.resultchecker.repository.DrawDateRepository;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;
import pl.lotto.winningnumbergenerator.WiningNumbersGeneratorFacade;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ResultCheckerFacade {

    private final ResultCheckerRepository resultCheckerRepository;
    private final DrawDateRepository drawDateRepository;
    private final NumberReceiverFacade numberReceiverFacade;
    private final WiningNumbersGeneratorFacade numbersGeneratorFacade;
    private final ResultCalculator calculator;
    private final Clock clock;

    public ResultCheckerFacade(DrawDateRepository drawDateRepository, NumberReceiverFacade numberReceiverFacade, WiningNumbersGeneratorFacade numbersGeneratorFacade, Clock clock, ResultCheckerRepository inputRepository, ResultCalculator calculator) {
        this.drawDateRepository = drawDateRepository;
        this.numberReceiverFacade = numberReceiverFacade;
        this.numbersGeneratorFacade = numbersGeneratorFacade;
        this.clock = clock;
        this.resultCheckerRepository = inputRepository;
        this.calculator = calculator;
    }

    public CheckerDto checkWinners(UUID uuid) {
        LocalDateTime drawDate = numberReceiverFacade.outputDrawTime(uuid);

        if (drawDateRepository.existsById(uuid)) {
            return resultCheckerRepository.findById(drawDateRepository.findById(uuid).get().dateTime()).get();
        }
        drawDateRepository.save(new CheckerRepoEntity(uuid, drawDate));
        Map<UUID, List<Integer>> inputs = fetchInputMap(drawDate);
        List<Integer> wonNumbers = fetchWonNumbers(drawDate);
        Map<UUID, Integer> map = calculator.calculateResults(inputs, wonNumbers);
        CheckerDto result = fetchCheckerDto(map, drawDate);
        resultCheckerRepository.save(result);
        return map.containsKey(uuid) ? result : ResultCheckerDtoMapper.mapToCheckerDto(new HashMap<>(), LocalDateTime.MIN);
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

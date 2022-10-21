package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.checkerdto.CheckerDto;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ResultCheckerFacade {

    private final ResultCheckerRepository resultCheckerRepository;
    private final NumberReceiverFacade numberReceiverFacade;
    private final ResultCalculator calculator;
    private final NumberProvider numberProvider;


    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, ResultCheckerRepository inputRepository, ResultCalculator calculator, NumberProvider numberProvider) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.resultCheckerRepository = inputRepository;
        this.calculator = calculator;
        this.numberProvider = numberProvider;
    }

    public CheckerDto checkWinners(UUID uuid) {
        LocalDateTime drawDate = numberReceiverFacade.outputDrawTime(uuid);
        // return empty map and min Local Date Time for invalid UUID, fast ends further calculations
        if (drawDate == null) {
            return new CheckerDto(new HashMap<>(), LocalDateTime.MIN);
        }
        // return calculated results if they exist
//        LocalDateTime id = drawDate.get();
        if (resultCheckerRepository.existsById(drawDate)) {
            return resultCheckerRepository.findById(drawDate).get();
        }
        // drawDateRepository.save(new CheckerRepoEntity(uuid, drawDate));
        Map<UUID, List<Integer>> inputs = fetchInputMap(drawDate);
        List<Integer> wonNumbers = numberProvider.getWinningNumbers(drawDate);
        Map<UUID, Integer> map = calculator.calculateResults(inputs, wonNumbers);
        CheckerDto result = fetchCheckerDto(map, drawDate);
        resultCheckerRepository.save(result);
        return map.containsKey(uuid) ? result : ResultCheckerDtoMapper.mapToCheckerDto(new HashMap<>(), LocalDateTime.MAX);
    }

    private Map<UUID, List<Integer>> fetchInputMap(LocalDateTime drawDate) {
        return ResultCheckerDtoMapper.mapReceiverDtoToMap(numberReceiverFacade.retrieveNumbersForDate(drawDate));
    }


    private CheckerDto fetchCheckerDto(Map<UUID, Integer> map, LocalDateTime dateTime) {
        return ResultCheckerDtoMapper.mapToCheckerDto(map, dateTime);
    }
}

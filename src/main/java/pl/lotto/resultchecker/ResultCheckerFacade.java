package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.checkerdto.CheckerDto;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;

import java.time.LocalDateTime;
import java.util.*;

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
        Optional<LocalDateTime> drawDate = numberReceiverFacade.outputDrawTime(uuid);
        // return empty map and min Local Date Time for invalid UUID, fast ends further calculations
        if (drawDate.isEmpty()) {
            return new CheckerDto(new HashMap<>(), LocalDateTime.MIN);
        }
        // return calculated results if they exist
        LocalDateTime id = drawDate.get();
        if (resultCheckerRepository.existsById(id)) {
            return resultCheckerRepository.findById(id).get();
        }
        // drawDateRepository.save(new CheckerRepoEntity(uuid, drawDate));
        Map<UUID, List<Integer>> inputs = fetchInputMap(id);
        List<Integer> wonNumbers = numberProvider.getWinningNumbers(id);
        Map<UUID, Integer> map = calculator.calculateResults(inputs, wonNumbers);
        CheckerDto result = fetchCheckerDto(map, id);
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

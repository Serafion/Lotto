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
        if (!drawDate.isPresent()) {
            return new CheckerDto(new HashMap<>(), LocalDateTime.MIN);
        }
        // return calculated results if they exist
        if (resultCheckerRepository.existsById(drawDate.get())) {
            return resultCheckerRepository.findById(drawDate.get()).get();
        }
        // drawDateRepository.save(new CheckerRepoEntity(uuid, drawDate));
        Map<UUID, List<Integer>> inputs = fetchInputMap(drawDate.get());
        List<Integer> wonNumbers = fetchWonNumbers(drawDate.get());
        Map<UUID, Integer> map = calculator.calculateResults(inputs, wonNumbers);
        CheckerDto result = fetchCheckerDto(map, drawDate.get());
        resultCheckerRepository.save(result);
        return map.containsKey(uuid) ? result : ResultCheckerDtoMapper.mapToCheckerDto(new HashMap<>(), LocalDateTime.MAX);
    }

    private List<Integer> fetchWonNumbers(LocalDateTime drawDate) {
//        return ResultCheckerDtoMapper.mapWinningNumbersList(numbersGeneratorFacade.retrieveWonNumbersForDate(drawDate));
        try {
            return numberProvider.getWinningNumbers(drawDate);
        } catch (Exception e) {
            return List.of();
        }
    }

    private Map<UUID, List<Integer>> fetchInputMap(LocalDateTime drawDate) {
        return ResultCheckerDtoMapper.mapReceiverDtoToMap(numberReceiverFacade.retrieveNumbersForDate(drawDate));
    }


    private CheckerDto fetchCheckerDto(Map<UUID, Integer> map, LocalDateTime dateTime) {
        return ResultCheckerDtoMapper.mapToCheckerDto(map, dateTime);
    }
}

package pl.lotto.resultchecker.resultcalculator;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.*;

public class ResultCalculator implements Calculatable {

    public static final int MINIMUM_HIT_NUMBERS = 4;
    private List<NumberReceiverResultDto> inputs;
    private NumberReceiverResultDto winningNumbers;

    public ResultCalculator() {
    }

    @Override
    public Map<UUID, WonNumbersCount> calculateResults(List<NumberReceiverResultDto> inputs, NumberReceiverResultDto winningNumbers) {
        this.inputs = inputs;
        this.winningNumbers = winningNumbers;
        return generateWinningContent();
    }

    private Map<UUID, WonNumbersCount> generateWinningContent() {
        Map<UUID, WonNumbersCount> map = new HashMap<>();
        for (NumberReceiverResultDto dto : inputs) {
            addContent(map, dto);
        }
        return map;
    }

    private void addContent(Map<UUID, WonNumbersCount> map, NumberReceiverResultDto dto) {
        UUID uuid = dto.uniqueLotteryId().get();
        WonNumbersCount count = calculateWonNumbers(winningNumbers.userNumbers(), dto.userNumbers());
        if (count.getValue() > MINIMUM_HIT_NUMBERS) {
            map.put(uuid, count);
        }
    }

    private WonNumbersCount calculateWonNumbers(List<Integer> input, List<Integer> wonNumbers) {
        int hitNumbers = calculateHitNumbers(input, wonNumbers);
        return fetchWonNumbersCount(hitNumbers);

    }

    private int calculateHitNumbers(List<Integer> input, List<Integer> drawnNumbers) {
        Set<Integer> wonNumbers = convertToSet(drawnNumbers);
        int hitNumbers = 0;
        for (Integer i : input) {
            if (wonNumbers.contains(i)) {
                hitNumbers++;
            }
        }
        return hitNumbers;
    }

    private WonNumbersCount fetchWonNumbersCount(int hitNumbers) {
        WonNumbersCount wonNumbersCount = WonNumbersCount.ZERO_NUMBERS_HIT;
        for (WonNumbersCount numberOfHits : WonNumbersCount.values()) {
            if (numberOfHits.getValue() == hitNumbers) {
                wonNumbersCount = numberOfHits;
            }
        }
        return wonNumbersCount;
    }

    private Set<Integer> convertToSet(List<Integer> drawnNumbers) {
        return new HashSet<>(drawnNumbers);
    }

}

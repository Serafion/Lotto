package pl.lotto.resultchecker;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
class ResultCalculator {

    public static final int MINIMUM_HIT_NUMBERS = 4;
    private Map<UUID, List<Integer>> inputs;
    private List<Integer> winningNumbers;

    public ResultCalculator() {
    }

    public Map<UUID, Integer> calculateResults(Map<UUID, List<Integer>> inputs, List<Integer> winningNumbers) {
        this.inputs = inputs;
        this.winningNumbers = winningNumbers;
        return generateWinningContent();
    }

    private Map<UUID, Integer> generateWinningContent() {
        Map<UUID, Integer> map = new HashMap<>();
        for (UUID uuid : inputs.keySet()) {
            addContent(map, uuid, inputs.get(uuid));
        }
        return map;
    }

    private void addContent(Map<UUID, Integer> map, UUID uuid, List<Integer> inputs) {
        Integer hitNumbers = calculateHitNumbers(winningNumbers, inputs);
        if (hitNumbers > MINIMUM_HIT_NUMBERS) {
            map.put(uuid, hitNumbers);
        }
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
    private Set<Integer> convertToSet(List<Integer> drawnNumbers) {
        return new HashSet<>(drawnNumbers);
    }

}

package pl.lotto.numberreceiver;

import java.util.List;
import java.util.stream.Collectors;

class DuplicateChecker implements Conditionable {

    @Override
    public ValidateMessage validateCondition(List<Integer> list) {
        int listLength = list.size();
        int numbersCount = countNumbersFromUser(list);
        return listLength == numbersCount ? ValidateMessage.CORRECT_MESSAGE : ValidateMessage.CONTAINS_DUPLICATES;
    }

    private static int countNumbersFromUser(List<Integer> list) {
        return list.stream()
                .collect(Collectors.toSet())
                .size();
    }
}

package pl.lotto.numberreceiver;

import java.util.HashSet;
import java.util.List;

class DuplicateChecker implements Conditionable {

    private static int countNumbersFromUser(List<Integer> list) {
        return new HashSet<>(list).size();
    }

    @Override
    public ValidateMessage validateCondition(List<Integer> list) {
        return list.size() == countNumbersFromUser(list) ?
                ValidateMessage.CORRECT_MESSAGE :
                ValidateMessage.CONTAINS_DUPLICATES;
    }
}

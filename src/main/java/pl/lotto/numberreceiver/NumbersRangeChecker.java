package pl.lotto.numberreceiver;

import java.util.List;

import static pl.lotto.numberreceiver.Constants.*;

class NumbersRangeChecker implements Conditionable {
    static boolean isInRange(Integer number) {
        return number >= LOW_NUMBER_BOUNDRY && number <= HIGH_NUMBER_BOUNDRY;
    }

    @Override
    public ValidateMessage validateCondition(List<Integer> list) {
        return list.size() == getValidNumbers(list) ?
                ValidateMessage.CORRECT_MESSAGE :
                ValidateMessage.NUMBERS_OUT_OF_RANGE;
    }

    private int getValidNumbers(List<Integer> list) {
        return (int) list.stream()
                .filter(NumbersRangeChecker::isInRange)
                .limit(NUMBERS_TO_DRAW)
                .count();
    }
}

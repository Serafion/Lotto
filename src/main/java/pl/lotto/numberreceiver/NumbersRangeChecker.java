package pl.lotto.numberreceiver;

import java.util.List;

import static pl.lotto.numberreceiver.Constants.*;

class NumbersRangeChecker implements Conditionable {
    @Override
    public ValidateMessage validateCondition(List<Integer> list) {
        int listLength = list.size();
        int listLengthOfValidNumbers = 0;
        for (Integer i : list) {
            if (isInRange(i)) {
                listLengthOfValidNumbers++;
                if (listLengthOfValidNumbers > NUMBERS_TO_DRAW) {
                    return ValidateMessage.NUMBERS_OUT_OF_RANGE;
                }
            }
        }
        return listLength == listLengthOfValidNumbers ? ValidateMessage.CORRECT_MESSAGE : ValidateMessage.NUMBERS_OUT_OF_RANGE;
    }

    boolean isInRange(Integer number) {
        return number >= LOW_NUMBER_BOUNDRY && number <= HIGH_NUMBER_BOUNDRY;
    }
}

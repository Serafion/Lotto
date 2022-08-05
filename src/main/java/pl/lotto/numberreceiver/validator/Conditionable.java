package pl.lotto.numberreceiver.validator;

import java.util.List;

public interface Conditionable {
    ValidateMessage validateCondition(List<Integer> list);
}

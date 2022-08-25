package pl.lotto.numberreceiver;

import java.util.List;

public interface Conditionable {
    ValidateMessage validateCondition(List<Integer> list);
}

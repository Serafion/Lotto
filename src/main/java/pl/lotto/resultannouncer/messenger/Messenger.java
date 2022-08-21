package pl.lotto.resultannouncer.messenger;

import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

public class Messenger {
    public static String fetchMessage(WonNumbersCount wonNumbersCount) {
        if (wonNumbersCount.equals(WonNumbersCount.ZERO_NUMBERS_HIT)) {
            return "sorry it's a loosing game";
        }
        return "You've hit: " + wonNumbersCount.getValue() + " numbers";
    }
}

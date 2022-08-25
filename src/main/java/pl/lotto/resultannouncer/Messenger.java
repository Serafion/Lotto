package pl.lotto.resultannouncer;

import static pl.lotto.resultannouncer.Constants.*;

class Messenger {
    public static String fetchMessage(Integer wonNumbersCount) {
        if (wonNumbersCount == NO_HIT_NUMBERS) {
            return LOST_COMMUNICATE;
        }
        return WIN_COMMUNICATE_PART_1 + wonNumbersCount + WIN_COMMUNICATE_PART_2;
    }
}

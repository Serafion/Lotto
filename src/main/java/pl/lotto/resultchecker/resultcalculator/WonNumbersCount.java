package pl.lotto.resultchecker.resultcalculator;

public enum WonNumbersCount {
    ZERO_NUMBERS_HIT(0),
    ONE_NUMBER_HIT(1),
    TWO_NUMBERS_HIT(2),
    THREE_NUMBERS_HIT(3),
    FOUR_NUMBERS_HIT(4),
    FIVE_NUMBERS_HIT(5),
    SIX_NUMBERS_HIT(6);
    private final int value;

    WonNumbersCount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

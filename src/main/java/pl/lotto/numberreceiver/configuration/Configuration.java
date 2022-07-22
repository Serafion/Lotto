package pl.lotto.numberreceiver.configuration;

public class Configuration {
    public static final String FAILED_CONTAINS_A_DUPLICATE_NUMBER = "Failed - contains a duplicate number";
    public static final String FAILED_CONTAINING_NUMBER_NOT_IN_RANGE = "Failed - containing number not in range";
    public static final String CORRECT_MESSAGE = "correct message";
    public static final String FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS = "Failed - did note received exactly six numbers";

    public static final int lowNumberBoundry = 1;
    public static final int highNumberBoundry = 99;
    public static final int numbersToDraw = 6;

    public Configuration() {
    }
}

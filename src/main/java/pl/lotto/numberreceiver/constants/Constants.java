package pl.lotto.numberreceiver.constants;

import java.time.DayOfWeek;

public class Constants {
    public static final String FAILED_CONTAINS_A_DUPLICATE_NUMBER = "Failed - contains a duplicate number";
    public static final String FAILED_CONTAINING_NUMBER_NOT_IN_RANGE = "Failed - containing number not in range";
    public static final String CORRECT_MESSAGE = "correct message";
    public static final String FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS = "Failed - did note received exactly six numbers";

    public static final int LOW_NUMBER_BOUNDRY = 1;
    public static final int HIGH_NUMBER_BOUNDRY = 99;
    public static final int NUMBERS_TO_DRAW = 6;
    public static final DayOfWeek DRAW_DAY_OF_WEEK = DayOfWeek.SATURDAY;
    public static final int HOUR_OF_DRAW = 12;
    public static final int MINUTE_OF_DRAW = 0;
    public static final int SECOND_OF_DRAW = 0;

    public static final String FINAL_MESSAGE_PART_1 = "You,ve put ";
    public static final String FINAL_MESSAGE_PART_2 = " you're token is: ";
    public static final String FINAL_MESSAGE_PART_3 = ". Date of drawing numbers is: ";

    public Constants() {
    }
}

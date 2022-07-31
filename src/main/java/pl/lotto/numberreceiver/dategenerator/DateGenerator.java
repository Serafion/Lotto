package pl.lotto.numberreceiver.dategenerator;

import java.time.LocalDateTime;

import static pl.lotto.numberreceiver.constants.Constants.*;

public class DateGenerator {

    private final LocalDateTime currentTime = LocalDateTime.now();

    public LocalDateTime retrieveNextDrawDate(){
        LocalDateTime dateOfDraw = retrieveDayOfDraw(currentTime.getDayOfMonth());
        while(!dateOfDraw.getDayOfWeek().equals(DRAW_DAY_OF_WEEK)||(dateOfDraw.compareTo(currentTime)<0)){
            dateOfDraw=dateOfDraw.plusDays(1);
        }
        return dateOfDraw;
    }
    private LocalDateTime retrieveDayOfDraw(int dayOfDraw) {
        return LocalDateTime.of(currentTime.getYear(), currentTime.getMonth(), dayOfDraw, HOUR_OF_DRAW, MINUTE_OF_DRAW
                ,SECOND_OF_DRAW);
    }

//    private int calculateDrawDay() {
//        int Today = currentDayOfMonth();
//        if(!currentDayOfWeek().equals(DRAW_DAY_OF_WEEK)){
//             Today += daysTillNextDraw();
//        }
//        if(currentDayOfWeek().equals(DRAW_DAY_OF_WEEK)&&currentTime.getHour()>HOUR_OF_DRAW){
//            Today+=7;
//        }
//        return Today;
//    }
//
//    private int daysTillNextDraw() {
//        return currentDayOfWeek().compareTo(DRAW_DAY_OF_WEEK) < 0 ?
//                DRAW_DAY_OF_WEEK.getValue() - currentDayOfWeek().getValue() :
//                DRAW_DAY_OF_WEEK.getValue();
//    }
//
//    private int currentDayOfMonth() {
//        return currentTime.getDayOfMonth();
//    }
//
//    private DayOfWeek currentDayOfWeek() {
//        return currentTime.getDayOfWeek();
//    }
}

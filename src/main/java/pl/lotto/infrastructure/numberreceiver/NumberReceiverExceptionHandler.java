package pl.lotto.infrastructure.numberreceiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.lotto.numberreceiver.UserInputNotFoundException;

@ControllerAdvice
@Slf4j
public class NumberReceiverExceptionHandler {

    @ExceptionHandler({UserInputNotFoundException.class})
    public ResponseEntity<NumberRecieverErrorDto> f(UserInputNotFoundException e) {
        log.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

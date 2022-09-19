package pl.lotto.infrastructure.numberreceiver.endpoint;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@RestController
public class NumberReceiverController {

    @Autowired
    NumberReceiverFacade numberReceiverFacade;

    @GetMapping("/hello")
    public ResponseEntity<String> f(@RequestBody InputNumbersRequest inputNumbersRequest) {
        List<Integer> numbersFromUser = ;
        numberReceiverFacade.inputNumbers(numbersFromUser);
        if(true){
            return ResponseEntity.ok("Hello");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

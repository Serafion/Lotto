package pl.lotto.infrastructure.numberreceiver.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
public class NumberReceiverController {

    @Autowired
    NumberReceiverFacade numberReceiverFacade;

    @Operation(summary = "Make new lottery ticket, put 6 numbers in range 1-99. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created a ticket"),
            @ApiResponse(responseCode = "422", description = "Unprocessable entity")})
    @RequestMapping(value = "/process", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<NumberReceiverResultDto> insertNumbers(@RequestBody InputNumbersRequest inputNumbersRequest) {
        Optional<List<Integer>> numbers = parseNumbers(inputNumbersRequest.getNumbers());
        if (numbers.isPresent()) {
            NumberReceiverResultDto ticket = numberReceiverFacade.inputNumbers(numbers.get());
            if (ticket.uniqueLotteryId().isPresent()) {
                log.info("Ticket for date: " + ticket.dateOfDraw().get() + " and with uuid" + ticket.uniqueLotteryId());
                return ResponseEntity.ok().body(ticket);
            }
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    public Optional<List<Integer>> parseNumbers(String numbers) {
        try {
            return Optional.of(Stream.of(numbers.split(",")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}

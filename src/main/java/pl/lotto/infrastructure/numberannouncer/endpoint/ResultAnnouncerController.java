package pl.lotto.infrastructure.numberannouncer.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;

import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class ResultAnnouncerController {

    @Autowired
    ResultAnnouncerFacade resultAnnouncerFacade;

    @Operation(summary = "Get winning numbers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found your ticket"),
            @ApiResponse(responseCode = "400", description = "Invalid UUID data provided"),
            @ApiResponse(responseCode = "404", description = "Ticket not found")})
    @RequestMapping(value = "/get_results", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<String> getResults(@RequestBody ResultRequest resultRequest) {
        Optional<UUID> uuid = getUUID(resultRequest.getUuid());
        return uuid.map(value -> ResponseEntity.ok(resultAnnouncerFacade.checkWinner(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid UUID data provided"));
    }

    private Optional<UUID> getUUID(String input) {
        try {
            return Optional.of(UUID.fromString(input));
        } catch (Exception e) {
            log.info("error in getting UUID");
            log.info(e.getMessage());
        }
        return Optional.empty();
    }
}

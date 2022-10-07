package pl.lotto.numberprovider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

public class NumberProviderFacade {

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<?> getWinningNumbers(LocalDateTime localDateTime) throws JsonProcessingException {

        try {
//            URI uri ="localhost:1443/get_numbers";
            String uri = "localhost:1443/get_numbers";
            HttpEntity<?> entity = new HttpEntity<>(mapper.writer(LocalDateTime.MIN));
            String result = restTemplate.getForObject(uri, String.class, localDateTime.toString());
            restTemplate.getForEntity(uri, )
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

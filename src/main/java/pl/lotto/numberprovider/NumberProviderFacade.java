package pl.lotto.numberprovider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class NumberProviderFacade {

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    public List getWinningNumbers(LocalDateTime localDateTime) throws JsonProcessingException {

        try {
//            String host = "localhost:8089";
            String date = localDateTime.toLocalDate().toString();
            String uri = "http://localhost:1443/get_numbers?date=" + date + "&pswd=abc";
//            String uri = "http://" + host + "/get_numbers?date=" + date + "&pswd=abc";
            ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
            log.info(restTemplate.getForEntity(uri, List.class).getStatusCode().toString());
            return result.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

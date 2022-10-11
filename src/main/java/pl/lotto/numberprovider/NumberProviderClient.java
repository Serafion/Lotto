package pl.lotto.numberprovider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.lotto.resultchecker.NumberProvider;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class NumberProviderClient implements NumberProvider {

    RestTemplate restTemplate = new RestTemplate();
    public List getWinningNumbers(LocalDateTime localDateTime) {

        try {

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

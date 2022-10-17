package pl.lotto.infrastructure.httpclient.resultchecker;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.lotto.resultchecker.NumberProvider;

import java.time.LocalDateTime;
import java.util.List;
import static java.util.Collections.emptyList;

@Slf4j
public class NumberProviderClient implements NumberProvider {

    private final RestTemplate restTemplate;

    public NumberProviderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Integer> getWinningNumbers(LocalDateTime localDateTime) {

//        try {
//
//            String date = localDateTime.toLocalDate().toString();
//            String uri = "http://localhost:1443/get_numbers?date=" + date + "&pswd=abc";
////            String uri = "http://" + host + "/get_numbers?date=" + date + "&pswd=abc";
//            ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
//            log.info(restTemplate.getForEntity(uri, List.class).getStatusCode().toString());
//            return result.getBody();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return List.of();
//        }


        String date = localDateTime.toLocalDate().toString();
        String uri = "http://localhost:1443/get_numbers?date=" + date + "&pswd=abc";

        try {
            ResponseEntity<List<Integer>> result = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    });
            return result.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return emptyList();
        }
    }
}

package pl.lotto.infrastructure.httpclient.resultchecker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.lotto.resultchecker.NumberProvider;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;

@Slf4j
public class NumberProviderClient implements NumberProvider {

    public static final String PSWD = "pswd";
    public static final String PSWD_VALUE = "abc";
    public static final String DATE_PARAM_NAME = "date";
    private final RestTemplate restTemplate;

    private final String api_name;


    public NumberProviderClient(RestTemplate restTemplate, @Value("${api.url_address}") String api_name) {
        this.restTemplate = restTemplate;
        this.api_name = api_name;
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
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(api_name)
                .queryParam(DATE_PARAM_NAME, date)
                .queryParam(PSWD, PSWD_VALUE).build();
//        log.info("uri was: "+uri);
//        "http://localhost:1443/get_numbers?date=" + date + "&pswd=abc";

        try {
            System.out.println(uriComponents.toUriString());
            ResponseEntity<List<Integer>> result = restTemplate.exchange(
                    uriComponents.toUriString(),
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

package pl.lotto.features;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.Body;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@Component
public class GetNumbersEndpoint {

    @Autowired
    WireMockServer wireMockServer;

    public void setUpGetNumbersEndpoint(List<Integer> winningNumbers) throws JsonProcessingException {
        StringValuePattern pswd = equalTo("abc");
        StringValuePattern date = equalTo("2022-02-12");
        Map<String, StringValuePattern> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        map.put(date.getName(), date);
        map.put(pswd.getName(), pswd);
        wireMockServer.stubFor(get("/get_numbers?date=2022-02-12&pswd=abc").willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withJsonBody(Body.fromJsonBytes(objectMapper.writeValueAsBytes(winningNumbers)).asJson()).withStatusMessage("it worked somehow")
//
        ));
    }
}

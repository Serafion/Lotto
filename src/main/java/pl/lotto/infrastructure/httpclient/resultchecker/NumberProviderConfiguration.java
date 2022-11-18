package pl.lotto.infrastructure.httpclient.resultchecker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.lotto.resultchecker.NumberProvider;

@Configuration
public class NumberProviderConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public NumberProvider numberProvider(RestTemplate restTemplate, @Value("${api.url_address}") String api_name) {
        return new NumberProviderClient(restTemplate, api_name);
    }
}

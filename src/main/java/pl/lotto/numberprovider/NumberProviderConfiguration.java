package pl.lotto.numberprovider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.resultchecker.NumberProvider;

@Configuration
public class NumberProviderConfiguration {

    @Bean
    public NumberProvider numberProvider() {
        return new NumberProviderClient();
    }
}

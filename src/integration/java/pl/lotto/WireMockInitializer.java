package pl.lotto;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

public class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        WireMockServer wireMockServer =
                new WireMockServer(1443);
        wireMockServer.start();

        applicationContext.addApplicationListener(applicationEvent -> {
            if (applicationEvent instanceof ContextClosedEvent) {
                wireMockServer.stop();
            }
        });

        applicationContext.getBeanFactory()
                .registerSingleton("wireMockServer", wireMockServer);

    }
}

package rate;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.bmstu.Application;
import org.bmstu.services.RateCurrencyClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class})
class RateClientIntegrationTest {

    @Autowired
    private WireMockServer mockRateService;

    @Autowired
    private RateCurrencyClient rateClient;

    @BeforeEach
    void setUp() throws IOException {
        RateMocks.setupMockRateResponseRub(mockRateService);
        RateMocks.setupMockRateResponseEur(mockRateService);
    }

    @Test
    public void whenGetRates_thenRatesShouldBeReturned() {
        HashMap<String, Double> rates = rateClient.getDataLatest("RUB").getRates();
        assertFalse(rates.isEmpty());
    }

    @Test
    public void whenGetBooks_thenTheCorrectBooksShouldBeReturned() {
        HashMap<String, Double> rates = rateClient.getDataLatest("EUR").getRates();
        Double euroRate = rates.get("EUR");
        assertEquals(0.82, euroRate);
    }
}
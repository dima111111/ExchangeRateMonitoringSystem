package rate;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class RateMocks {

    public static void setupMockRateResponseRub(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/latest.json?app_id=b0609eaa2b8e415e9f4edc86f2fb884d&base=USD&symbols=RUB"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        RateMocks.class.getClassLoader().getResourceAsStream("static/get-rate-response.json"),
                                        defaultCharset()))));
    }

    public static void setupMockRateResponseEur(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/latest.json?app_id=b0609eaa2b8e415e9f4edc86f2fb884d&base=USD&symbols=EUR"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        RateMocks.class.getClassLoader().getResourceAsStream("static/get-rate-response.json"),
                                        defaultCharset()))));
    }
}
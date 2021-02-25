package gif;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class GifMocks {

    public static void setupMockGifResponseRich(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/?api_key=tshHfDA8I3nqIQWDq4VBw36sy6sgqcI5&q=rich&offset=3&limit=1"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        GifMocks.class.getClassLoader().getResourceAsStream("static/get-gif-response.json"),
                                        defaultCharset()))));
    }
}
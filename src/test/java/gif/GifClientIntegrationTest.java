package gif;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.bmstu.Application;
import org.bmstu.model.GifObject;
import org.bmstu.services.GiphyClient;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class})
class GifClientIntegrationTest {

    @Autowired
    private WireMockServer mockGiphyService;

    @Autowired
    private GiphyClient giphyClient;

    @BeforeEach
    void setUp() throws IOException {
        GifMocks.setupMockGifResponseRich(mockGiphyService);
    }

    @Test
    public void whenSearchGif_thenGifShouldBeReturned() {
        try {
            GifObject gif1 = giphyClient.searchGif("rich", 3).getGifObject();
            assertNotNull(gif1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void whenGifGetUrl_thenTheCorrectGifUrlShouldBeReturned() {
        GifObject gif = giphyClient.searchGif("rich", 3).getGifObject();
        assertEquals("https://giphy.com/embed/lptjRBxFKCJmFoibP3", gif.getUrl());
    }
}
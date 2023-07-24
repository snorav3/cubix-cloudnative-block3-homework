package hu.cubix.cloud.api.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import hu.cubix.cloud.call.api.BackappApiMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(properties = {"cubix.homework=test", "app.default.message=default", "extra.from.base=value"})
@AutoConfigureMockMvc
class FrontappControllerCustomDefaultIT {

    private static final String MESSAGE = "default";
    private static final String EXTRA_IMAGE_DATA = "value";
    private static WireMockServer messageApi;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setUp() throws IOException {
        messageApi = BackappApiMock.createMockServer();
        BackappApiMock.setupMockResponses(messageApi, MESSAGE, EXTRA_IMAGE_DATA);
        messageApi.start();
    }
    @AfterAll
    static void afterAll() {
        messageApi.stop();
    }

    @Test
    void local() throws Exception {
        mockMvc.perform(get("/frontapp/local"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(FrontappMatchers.createMsForReplyJsonPathMatcherForLocal())
                .andExpect(FrontappMatchers.createBackappMessageJsonPathMatcher("frontapp:" + MESSAGE))
                .andExpect(FrontappMatchers.createFrontappHomeworkOwnerJsonPathMatcher("test"))
                .andExpect(FrontappMatchers.createBackappHomeworkOwnerJsonPathMatcher(null))
                .andExpect(FrontappMatchers.createFrontappHostAddressJsonPathMatcher())
                .andExpect(FrontappMatchers.createBackappHostAddressJsonPathMatcher())
                .andExpect(FrontappMatchers.createDoExtraImageDataJsonPathMatcher(true));
    }

    @Test
    void call() throws Exception {
        mockMvc.perform(get("/frontapp"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(FrontappMatchers.createMsForReplyJsonPathMatcherForExample())
                .andExpect(FrontappMatchers.createBackappMessageJsonPathMatcher(MESSAGE))
                .andExpect(FrontappMatchers.createFrontappHomeworkOwnerJsonPathMatcher("test"))
                .andExpect(FrontappMatchers.createBackappHomeworkOwnerJsonPathMatcher("backapp-owner"))
                .andExpect(FrontappMatchers.createFrontappHostAddressJsonPathMatcher())
                .andExpect(FrontappMatchers.createBackappHostAddressJsonPathMatcher())
                .andExpect(FrontappMatchers.createDoExtraImageDataJsonPathMatcher(true));
    }
}
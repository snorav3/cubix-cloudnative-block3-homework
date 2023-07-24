package hu.cubix.cloud.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class BackappControllerIT {

    private static final String DEFAULT_OWNER = "backapp-me";
    private static final String DEFAULT_IMAGE_EXTRA_DATA = "NOT_SET";
    @Autowired
    private MockMvc mvc;

    @Test
    void burntInValues() throws Exception {
        mvc.perform(get("/backapp"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BackappMatchers.createTimeJsonPathMatcher())
                .andExpect(BackappMatchers.createHostAddressJsonPathMatcher())
                .andExpect(BackappMatchers.createExtraImageDataJsonPathMatcher(DEFAULT_IMAGE_EXTRA_DATA))
                .andExpect(BackappMatchers.createHomeworkOwnerJsonPathMatcher(DEFAULT_OWNER))
                .andExpect(BackappMatchers.createMessageJsonPathMatcher("DEFAULT-BACKAPP-MESSAGE"));
    }

    @Test
    void customMessage() throws Exception {
        String message = "hello";
        mvc.perform(get("/backapp").queryParam("message", message))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BackappMatchers.createTimeJsonPathMatcher())
                .andExpect(BackappMatchers.createHostAddressJsonPathMatcher())
                .andExpect(BackappMatchers.createExtraImageDataJsonPathMatcher(DEFAULT_IMAGE_EXTRA_DATA))
                .andExpect(BackappMatchers.createHomeworkOwnerJsonPathMatcher(DEFAULT_OWNER))
                .andExpect(BackappMatchers.createMessageJsonPathMatcher(message));
    }


}
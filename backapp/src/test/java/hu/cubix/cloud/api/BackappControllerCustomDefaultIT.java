package hu.cubix.cloud.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(properties = {"cubix.homework=test", "app.default.message=default", "extra.from.base=value"})
@AutoConfigureMockMvc
class BackappControllerCustomDefaultIT {

    @Autowired
    private MockMvc mvc;

    @Test
    void defaultValues() throws Exception {
        mvc.perform(get("/backapp"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BackappMatchers.createTimeJsonPathMatcher())
                .andExpect(BackappMatchers.createHostAddressJsonPathMatcher())
                .andExpect(BackappMatchers.createExtraImageDataJsonPathMatcher("value"))
                .andExpect(BackappMatchers.createHomeworkOwnerJsonPathMatcher("test"))
                .andExpect(BackappMatchers.createMessageJsonPathMatcher("default"));
    }

}
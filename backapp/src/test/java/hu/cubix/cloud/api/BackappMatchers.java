package hu.cubix.cloud.api;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;

class BackappMatchers {
    private BackappMatchers() {
    }

    static ResultMatcher createTimeJsonPathMatcher() {
        return MockMvcResultMatchers.jsonPath("$.time", matchesRegex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d*"));
    }

    static ResultMatcher createStringJsonPathMatcherForRoot(String fieldName, String value) {
        return MockMvcResultMatchers.jsonPath("$." + fieldName, is(value));
    }

    static ResultMatcher createMessageJsonPathMatcher(String message) {
        return createStringJsonPathMatcherForRoot("message", message);
    }

    static ResultMatcher createHomeworkOwnerJsonPathMatcher(String homeworkOwner) {
        return createStringJsonPathMatcherForRoot("homeworkOwner", homeworkOwner);
    }

    static ResultMatcher createExtraImageDataJsonPathMatcher(String extraImageData) {
        return createStringJsonPathMatcherForRoot("extraImageData", extraImageData);
    }

    static ResultMatcher createHostAddressJsonPathMatcher() {
        return MockMvcResultMatchers.jsonPath("$.hostAddress",
                // IP addresses are simplified here
                anyOf(matchesRegex("(\\d{1,3}\\.){3}\\d{1,3}"),
                        matchesRegex("((\\d|[a-f]|[A-F]){4}:){7}(\\d|[a-f]|[A-F]){4}"),
                        matchesRegex("((\\d|[a-f]|[A-F]){4}:){4}:"),
                        is(nullValue())));
    }

}

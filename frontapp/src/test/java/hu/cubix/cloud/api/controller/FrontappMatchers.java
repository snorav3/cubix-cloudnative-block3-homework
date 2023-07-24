package hu.cubix.cloud.api.controller;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;

class FrontappMatchers {
    private FrontappMatchers() {
    }

    static ResultMatcher createJsonPathMatcherForRoot(String fieldName, Object value) {
        return MockMvcResultMatchers.jsonPath("$." + fieldName, is(value));
    }

    static ResultMatcher createMsForReplyJsonPathMatcherForLocal() {
        return MockMvcResultMatchers.jsonPath("$.msForReply", is(0));
    }

    static ResultMatcher createMsForReplyJsonPathMatcherForExample() {
        return MockMvcResultMatchers.jsonPath("$.msForReply", greaterThan(365L * 24 * 60 * 60 * 1000), Long.class);
    }

    static ResultMatcher createBackappMessageJsonPathMatcher(String message) {
        return createJsonPathMatcherForRoot("backappMessage", message);
    }

    static ResultMatcher createFrontappHomeworkOwnerJsonPathMatcher(String homeworkOwner) {
        return createJsonPathMatcherForRoot("frontappHomeworkOwner", homeworkOwner);
    }

    static ResultMatcher createBackappHomeworkOwnerJsonPathMatcher(String homeworkOwner) {
        return createJsonPathMatcherForRoot("backappHomeworkOwner", homeworkOwner);
    }

    static ResultMatcher createDoExtraImageDataJsonPathMatcher(boolean shouldTheyMatch) {
        return createJsonPathMatcherForRoot("doExtraImageDataMatch", shouldTheyMatch);
    }

    static ResultMatcher createFrontappHostAddressJsonPathMatcher() {
        return createHostAddressJsonPathMatcher("frontappHostAddress");
    }

    static ResultMatcher createBackappHostAddressJsonPathMatcher() {
        return createHostAddressJsonPathMatcher("backappHostAddress");
    }

    private static ResultMatcher createHostAddressJsonPathMatcher(String fieldName) {
        return MockMvcResultMatchers.jsonPath("$." + fieldName,
                // IP addresses are simplified here
                anyOf(matchesRegex("(\\d{1,3}\\.){3}\\d{1,3}"),
                        matchesRegex("((\\d|[a-f]|[A-F]){4}:){7}(\\d|[a-f]|[A-F]){4}"),
                        matchesRegex("((\\d|[a-f]|[A-F]){4}:){4}:"),
                        is(nullValue())));
    }

}

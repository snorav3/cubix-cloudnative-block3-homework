package hu.cubix.cloud.api;

import hu.cubix.cloud.model.BackappResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/backapp")
public class BackappController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackappController.class);

    private final String homeworkOwner;
    private final String defaultMessage;
    private final String extraImageData;

    public BackappController(@Value("${cubix.homework:backapp-me}") String homeworkOwner,
                             @Value("${app.default.message:DEFAULT-BACKAPP-MESSAGE}") String defaultMessage,
                             @Value("${extra.from.base:NOT_SET}") String extraImageData) {
        this.homeworkOwner = homeworkOwner;
        this.defaultMessage = defaultMessage;
        this.extraImageData = extraImageData;
    }

    @GetMapping
    public BackappResponse backapp(@RequestParam(required = false, name = "message") String message) {
        LOGGER.info("Request arrived with message {}", message);
        if (!StringUtils.hasText(message)) {
            LOGGER.info("Message was empty, fall back to default");
            if (!StringUtils.hasText(defaultMessage)) {
                LOGGER.info("Default message was empty, falling back to hardcoded default");
                message = "hardcoded-default-backapp-message";
            }
            else {
                message = defaultMessage;
            }
        }
        BackappResponse backappResponse = new BackappResponse(LocalDateTime.now(), message,
                homeworkOwner, getHostAddress(), extraImageData);
        LOGGER.info("Returning response: {}", backappResponse);
        return backappResponse;
    }

    private String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception exception) {
            LOGGER.warn("Unable to determine localhost address", exception);
            return null;
        }
    }

}

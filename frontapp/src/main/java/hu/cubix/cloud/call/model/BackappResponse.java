package hu.cubix.cloud.call.model;

import java.time.LocalDateTime;

public record BackappResponse(LocalDateTime time, String message,
                              String homeworkOwner, String hostAddress, String extraImageData) {
}

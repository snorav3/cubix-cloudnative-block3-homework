package hu.cubix.cloud.model;

public record FrontappResponse(long msForReply, String backappMessage,
                               String frontappHomeworkOwner, String frontappHostAddress,
                               String backappHomeworkOwner, String backappHostAddress,
                               boolean doExtraImageDataMatch) {
}

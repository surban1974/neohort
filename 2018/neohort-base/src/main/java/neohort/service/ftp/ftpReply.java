package neohort.service.ftp;

public class ftpReply {
    private String replyCode;
    private String replyText;


    ftpReply(String replyCode, String replyText) {
        this.replyCode = replyCode;
        this.replyText = replyText;
    }
    public String getReplyCode() {
        return replyCode;
    }
    public String getReplyText() {
        return replyText;
    }
}

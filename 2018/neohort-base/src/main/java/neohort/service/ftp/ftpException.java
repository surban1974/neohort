package neohort.service.ftp;

public class ftpException extends Exception {
 	private static final long serialVersionUID = -1L;
	private int replyCode = -1;

    public ftpException(String msg) {
        super(msg);
    }
    public ftpException(String msg, String replyCode) {

        super(msg);
        try {
            this.replyCode = Integer.parseInt(replyCode);
        }
        catch (NumberFormatException ex) {
            this.replyCode = -1;
        }
    }
    public int getReplyCode() {
        return replyCode;
    }
}

package neohort.service.ftp;

public class ftp_message {
	private java.lang.String HOST;
	private java.lang.String USER;
	private java.lang.String PSWD;
	private java.lang.String WORKDIR;
	private java.lang.String TRANSFERTYPE;
public ftp_message() {
	super();
	reimposta();
}
public ftp_message(String HOST,String USER,String PSWD,String WORKDIR) {
	if(HOST!=null)		this.HOST = HOST;
	if(USER != null)	this.USER = USER;
	if(PSWD != null) 	this.PSWD = PSWD;
	if(WORKDIR != null) this.WORKDIR = WORKDIR;
	this.TRANSFERTYPE = "BINARY";

}
public ftp_message(String HOST,String USER,String PSWD,String WORKDIR, String TRANSFERTYPE) {
	if(HOST!=null)		this.HOST = HOST;
	if(USER != null)	this.USER = USER;
	if(PSWD != null) 	this.PSWD = PSWD;
	if(WORKDIR != null) this.WORKDIR = WORKDIR;
	if(TRANSFERTYPE != null && !TRANSFERTYPE.equals("")) this.WORKDIR = WORKDIR;

}
public java.lang.String getHOST() {
	return HOST;
}
public java.lang.String getPSWD() {
	return PSWD;
}
public java.lang.String getTRANSFERTYPE() {
	return TRANSFERTYPE;
}
public java.lang.String getUSER() {
	return USER;
}
public java.lang.String getWORKDIR() {
	return WORKDIR;
}
public void reimposta() {
	HOST 			= "";
	USER 			= "";
	PSWD 			= "";
	WORKDIR 		= "";
	TRANSFERTYPE 	= "BINARY";
}
public void setHOST(java.lang.String newHOST) {
	HOST = newHOST;
}
public void setPSWD(java.lang.String newPSW) {
	PSWD = newPSW;
}
public void setTRANSFERTYPE(java.lang.String newTRANSFERTYPE) {
	TRANSFERTYPE = newTRANSFERTYPE;
}
public void setUSER(java.lang.String newUSER) {
	USER = newUSER;
}
public void setWORKDIR(java.lang.String newWORKDIR) {
	WORKDIR = newWORKDIR;
}
}

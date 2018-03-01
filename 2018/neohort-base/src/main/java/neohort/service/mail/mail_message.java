package neohort.service.mail; 

import java.io.Serializable;
import java.util.Vector;

public class mail_message implements Serializable{
	private static final long serialVersionUID = -1L;
	private String IMAPHOST;
	private String SMTPHOST;
	private String SMTPPORT;
	private String SMTPUSESOCKET;
	private String MAILUSER;
	private String MAILPSWD;
	private Vector<String> ATTACHMENTS;
	private Vector<Object> ATTACHMENTS_AS_BYTE;
	private String BODY;
	private String SUBJECT;
	private String MAILADDRESS_FROM;
	private String MAILADDRESS_TO;
	private String MAILADDRESS_CC;
	private String MAILADDRESS_BCC;
	private String BODY_CONTENT_TYPE;
	
public mail_message() {
	super();
	reimposta();
}
public Vector<String> getATTACHMENTS() {
	return ATTACHMENTS;
}
public java.lang.String getBODY() {
	return BODY;
}
public java.lang.String getIMAPHOST() {
	return IMAPHOST;
}
public java.lang.String getMAILADDRESS_BCC() {
	return MAILADDRESS_BCC;
}
public java.lang.String getMAILADDRESS_CC() {
	return MAILADDRESS_CC;
}
public java.lang.String getMAILADDRESS_FROM() {
	return MAILADDRESS_FROM;
}
public java.lang.String getMAILADDRESS_TO() {
	return MAILADDRESS_TO;
}
public java.lang.String getMAILPSWD() {
	return MAILPSWD;
}
public java.lang.String getMAILUSER() {
	return MAILUSER;
}
public java.lang.String getSMTPHOST() {
	return SMTPHOST;
}
public java.lang.String getSUBJECT() {
	return SUBJECT;
}
public void reimposta() {
	IMAPHOST = "";
	SMTPHOST = "";
	SMTPPORT = "";
	SMTPUSESOCKET = "";
	MAILUSER = "";
	MAILPSWD = "";
	ATTACHMENTS = new Vector<String>();
	ATTACHMENTS_AS_BYTE = new Vector<Object>();
	BODY = "";
	SUBJECT = "";
	MAILADDRESS_FROM = "";
	MAILADDRESS_TO = "";
	MAILADDRESS_CC = "";
	MAILADDRESS_BCC = "";
	BODY_CONTENT_TYPE = "";
}
public void setATTACHMENTS(Vector<String> newATTACHMENTS) {
	ATTACHMENTS = newATTACHMENTS;
}
public void setBODY(java.lang.String newBODY) {
	BODY = newBODY;
}
public void setIMAPHOST(java.lang.String newIMAPHOST) {
	IMAPHOST = newIMAPHOST;
}
public void setMAILADDRESS_BCC(java.lang.String newMAILADDRESS) {
	MAILADDRESS_BCC = newMAILADDRESS;
}
public void setMAILADDRESS_CC(java.lang.String newMAILADDRESS) {
	MAILADDRESS_CC = newMAILADDRESS;
}
public void setMAILADDRESS_FROM(java.lang.String newMAILADDRESS) {
	MAILADDRESS_FROM = newMAILADDRESS;
}
public void setMAILADDRESS_TO(java.lang.String newMAILADDRESS) {
	MAILADDRESS_TO = newMAILADDRESS;
}
public void setMAILPSWD(java.lang.String newMAILPSWD) {
	MAILPSWD = newMAILPSWD;
}
public void setMAILUSER(java.lang.String newMAILUSER) {
	MAILUSER = newMAILUSER;
}
public void setSMTPHOST(java.lang.String newSMTPHOST) {
	SMTPHOST = newSMTPHOST;
}
public void setSUBJECT(java.lang.String newSUBJECT) {
	SUBJECT = newSUBJECT;
}
public String getSMTPPORT() {
	return SMTPPORT;
}
public void setSMTPPORT(String smtpport) {
	SMTPPORT = smtpport;
}
public String getSMTPUSESOCKET() {
	return SMTPUSESOCKET;
}
public void setSMTPUSESOCKET(String smtpusesocket) {
	SMTPUSESOCKET = smtpusesocket;
}
public Vector<Object> getATTACHMENTS_AS_BYTE() {
	return ATTACHMENTS_AS_BYTE;
}
public void setATTACHMENTS_AS_BYTE(Vector<Object> attachments_as_byte) {
	ATTACHMENTS_AS_BYTE = attachments_as_byte;
}
public String getBODY_CONTENT_TYPE() {
	return BODY_CONTENT_TYPE;
}
public void setBODY_CONTENT_TYPE(String body_content_type) {
	BODY_CONTENT_TYPE = body_content_type;
}
}

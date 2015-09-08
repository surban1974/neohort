package neohort.service.ftp;

import java.util.*;


import neohort.exception.exception_manager_simple;
import neohort.log.stubs.iStub;



public class ftp_manager {
	private java.util.Vector trace = new Vector();
	private java.lang.String ID;
	private ftp_message FTP;

public	class operation{
		public String action = "";
		public String parameterIn = "";
		public String parameterOut = "";
public boolean perform(ftpClient ftp_cl) throws Exception{
	if(ftp_cl==null) return false;
	boolean result = true;
	try{
		if(action.trim().toUpperCase().equals("CHDIR")){
			ftp_cl.chdir(parameterIn);
		}
		if(action.trim().toUpperCase().equals("DELETE")){
			ftp_cl.delete(parameterIn);
		}
		if(action.trim().toUpperCase().equals("MKDIR")){
			ftp_cl.mkdir(parameterIn);
		}
		if(action.trim().toUpperCase().equals("RENAME")){
			ftp_cl.rename(parameterIn,parameterOut);
		}
		if(action.trim().toUpperCase().equals("RMDIR")){
			ftp_cl.rmdir(parameterIn);
		}
		if(action.trim().toUpperCase().equals("PUT")){
			ftp_cl.put(parameterIn,parameterOut);
		}
		if(action.trim().toUpperCase().equals("GET")){
			ftp_cl.get(parameterIn,parameterOut);
		}
	}catch(Exception e){
		exception_manager_simple.exception_to_log(null,e,"FTP:perform() "+action +" par1:"+parameterIn+" par2"+parameterOut,iStub.log_ERROR);
		result = false;
		throw e;
	}
	return result;
}
	}

ftp_manager() {
	super();
	ID = new java.sql.Timestamp(new java.util.Date().getTime()).toString();
}
public ftp_manager(ftp_message FTP) {
	super();
	ID = new java.sql.Timestamp(new java.util.Date().getTime()).toString();
	this.FTP = FTP;
}
public void addOperation(String action,String parameterIn,String parameterOut
) {
	if(	action==null || parameterIn==null || parameterOut==null) return;
	operation op = new operation();
		op.action = action;
		op.parameterIn = parameterIn;
		op.parameterOut = parameterOut;
	trace.add(op);
}
public Object get(operation action) throws Exception{
	Object result = null;
	try {
		ftpClient ftp_cl = new ftpClient(FTP.getHOST());
		ftp_cl.setType(ftpTransferType.BINARY);
		if(FTP.getTRANSFERTYPE()!=null && FTP.getTRANSFERTYPE().length()>0){
			if(FTP.getTRANSFERTYPE().trim().toUpperCase().equals("BINARY"))
				ftp_cl.setType(ftpTransferType.BINARY);
			if(FTP.getTRANSFERTYPE().trim().toUpperCase().equals("ASCII"))
				ftp_cl.setType(ftpTransferType.ASCII);
		}
		ftp_cl.login(FTP.getUSER(),FTP.getPSWD());
		if(action.action.trim().toUpperCase().equals("GET")){
			result = ftp_cl.get(action.parameterIn);
		}
		if(action.action.trim().toUpperCase().equals("LIST")){
			result = ftp_cl.list(action.parameterIn);
		}
		if(action.action.trim().toUpperCase().equals("DIR")){
			result = ftp_cl.list(action.parameterIn);
		}
		ftp_cl.quit();
	}catch(Exception e){
		exception_manager_simple.exception_to_log(null,e,null,iStub.log_ERROR);
		result = null;
		throw e;
	}
	return result;

}
public ftp_message getFTP() {
	return FTP;
}
public java.lang.String getID() {
	return ID;
}
public java.util.Vector getTrace() {
	return trace;
}
public boolean run() throws Exception{
	boolean result = false;
	try {
		ftpClient ftp_cl = new ftpClient(FTP.getHOST());
		ftp_cl.setType(ftpTransferType.BINARY);
		if(FTP.getTRANSFERTYPE()!=null && FTP.getTRANSFERTYPE().length()>0){
			if(FTP.getTRANSFERTYPE().trim().toUpperCase().equals("BINARY"))
				ftp_cl.setType(ftpTransferType.BINARY);
			if(FTP.getTRANSFERTYPE().trim().toUpperCase().equals("ASCII"))
				ftp_cl.setType(ftpTransferType.ASCII);
		}
		ftp_cl.login(FTP.getUSER(),FTP.getPSWD());
		for(int j=0;j<this.getTrace().size();j++)
			((operation)this.getTrace().elementAt(j)).perform(ftp_cl);
		ftp_cl.quit();
	}catch(Exception e){
		result = false;
		throw e;
	}
	return result;
}
public void setID(java.lang.String newID) {
	ID = newID;
}
public void setTrace(java.util.Vector newTrace) {
	trace = newTrace;
}
}

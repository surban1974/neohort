/**
* Creation date: (22/12/2005)
* @author: Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com
*/

/********************************************************************************
*
*	Copyright (C) 2005  Svyatoslav Urbanovych
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.

* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.

* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*********************************************************************************/

package neohort.universal.output.lib;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import neohort.log.stubs.iStub;

import neohort.service.mail.mail_manager_smtp;
import neohort.service.mail.mail_message;

public class mail extends report_element_base{
	private static final long serialVersionUID = 4511188447041392485L;
	private String IMAPHOST;
	private String SMTPHOST;
	private String MAILUSER;
	private String MAILPSWD;
	private String ATTACHMENTS;
	private String BODY;
	private String SUBJECT;
	private String MAILADDRESS_FROM;
	private String MAILADDRESS_TO;
	private String MAILADDRESS_CC;
	private String MAILADDRESS_BCC;
	private String SEND;
	private mail_message mess;
	
public mail() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		mess = new mail_message();
		mess.setIMAPHOST(getIMAPHOST());
		mess.setSMTPHOST(getSMTPHOST());
		mess.setMAILUSER(getMAILUSER());
		mess.setMAILPSWD(getMAILPSWD());
		
		mess.setBODY(getBODY());
		mess.setSUBJECT(getSUBJECT());
		mess.setMAILADDRESS_FROM(getMAILADDRESS_FROM());
		mess.setMAILADDRESS_TO(getMAILADDRESS_TO());
		mess.setMAILADDRESS_CC(getMAILADDRESS_CC());
		mess.setMAILADDRESS_BCC(getMAILADDRESS_BCC());
		
		if(!getATTACHMENTS().equals("")){
			StringTokenizer st = new StringTokenizer(getATTACHMENTS(),";");
			Vector att = new Vector();
			while(st.hasMoreTokens()) att.add(st.nextToken());
			mess.setATTACHMENTS(att);
		}	
		
		if(getSEND().toUpperCase().equals("TRUE")){
			mail_manager_smtp manager = new mail_manager_smtp();
			manager.service_send(mess);
		}	
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());		

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void send(){
	try{
		new mail_manager_smtp().service_send(mess);
	}catch(Exception e){
	}
}
public void reimposta() {
	setName("MAIL");
	IMAPHOST="";
	SMTPHOST="";
	MAILUSER="";
	MAILPSWD="";
	ATTACHMENTS="";
	BODY="";
	SUBJECT="";
	MAILADDRESS_FROM="";
	MAILADDRESS_TO="";
	MAILADDRESS_CC="";
	MAILADDRESS_BCC="";
	SEND="";
}
	public String getATTACHMENTS() {
		return ATTACHMENTS;
	}

	public String getBODY() {
		return BODY;
	}

	public String getIMAPHOST() {
		return IMAPHOST;
	}

	public String getMAILADDRESS_BCC() {
		return MAILADDRESS_BCC;
	}

	public String getMAILADDRESS_CC() {
		return MAILADDRESS_CC;
	}

	public String getMAILADDRESS_FROM() {
		return MAILADDRESS_FROM;
	}

	public String getMAILADDRESS_TO() {
		return MAILADDRESS_TO;
	}

	public String getMAILPSWD() {
		return MAILPSWD;
	}

	public String getMAILUSER() {
		return MAILUSER;
	}

	public String getSMTPHOST() {
		return SMTPHOST;
	}

	public String getSUBJECT() {
		return SUBJECT;
	}

	public void setATTACHMENTS(String string) {
		ATTACHMENTS = string;
	}

	public void setBODY(String string) {
		BODY = string;
	}

	public void setIMAPHOST(String string) {
		IMAPHOST = string;
	}

	public void setMAILADDRESS_BCC(String string) {
		MAILADDRESS_BCC = string;
	}

	public void setMAILADDRESS_CC(String string) {
		MAILADDRESS_CC = string;
	}

	public void setMAILADDRESS_FROM(String string) {
		MAILADDRESS_FROM = string;
	}

	public void setMAILADDRESS_TO(String string) {
		MAILADDRESS_TO = string;
	}

	public void setMAILPSWD(String string) {
		MAILPSWD = string;
	}

	public void setMAILUSER(String string) {
		MAILUSER = string;
	}

	public void setSMTPHOST(String string) {
		SMTPHOST = string;
	}

	public void setSUBJECT(String string) {
		SUBJECT = string;
	}

	public String getSEND() {
		return SEND;
	}

	public void setSEND(String string) {
		SEND = string;
	}

}

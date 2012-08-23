
package neohort.service.mail; 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class mail_manager_smtp {
	
	
    private class SMTPAuthenticator extends javax.mail.Authenticator{
    	private String user;
    	private String password;
    	SMTPAuthenticator(String _user, String _password){
    		super();
    		user=_user;
    		password=_password;
    	}
        public PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(user, password);
        }
    }
	
	
public mail_manager_smtp() {
	super();
}
public static String _separator() {
	String separator = "/";
	if(System.getProperty("file.separator")!=null) separator = System.getProperty("file.separator");	
	return separator;
}
public boolean service_send(mail_message out) throws Exception {
	boolean result = true;
	if(	out==null ||
		out.getSMTPHOST()==null || out.getSMTPHOST().equals("") ||
		out.getMAILADDRESS_FROM()==null || out.getMAILADDRESS_FROM().equals("") ||
		out.getMAILADDRESS_TO()==null || out.getMAILADDRESS_TO().equals("")
		) return false; 
	
		
		
        Properties props = new Properties();
        	if(!out.getMAILUSER().equals("")) props.put("mail.smtp.user", out.getMAILUSER());
        	props.put("mail.smtp.host", out.getSMTPHOST());
       	
        	
        	if(out.getSMTPPORT()!=null && !out.getSMTPPORT().equals("")) 
        		props.put("mail.smtp.port", out.getSMTPPORT());
//        	props.put("mail.smtp.starttls.enable","true");
        	if(out.getMAILUSER().equals("") && out.getMAILPSWD().equals("")){
        		props.put("mail.smtp.auth", "false");
        	}else props.put("mail.smtp.auth", "true");

        	if(out.getSMTPPORT()!=null && !out.getSMTPPORT().equals("")){
        		if(	out.getSMTPUSESOCKET()!=null && 
        			(	out.getSMTPUSESOCKET().toUpperCase().equals("Y") ||
        				out.getSMTPUSESOCKET().toUpperCase().equals("TRUE")
        			)	
        		){
        			props.put("mail.smtp.starttls.enable","true");
		        	props.put("mail.smtp.socketFactory.port", out.getSMTPPORT());
		        	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        	props.put("mail.smtp.socketFactory.fallback", "false");
        		}
        	}
 
		
            try{
            	Session session = null;
            	if(!out.getMAILUSER().equals("") && !out.getMAILPSWD().equals("")){
            		Authenticator auth = new SMTPAuthenticator(out.getMAILUSER(),out.getMAILPSWD());
            		session = Session.getInstance(props, auth);
            	}else session = Session.getInstance(props);	
                //session.setDebug(true);
     
                MimeMessage msg = new MimeMessage(session);
                	msg.setSubject(out.getSUBJECT());
                	msg.setFrom(new InternetAddress(out.getMAILADDRESS_FROM()));
                	msg.setRecipients(Message.RecipientType.TO, prepareInternetAddress(out.getMAILADDRESS_TO()));
                   	msg.setRecipients(Message.RecipientType.CC, prepareInternetAddress(out.getMAILADDRESS_CC()));
                   	msg.setRecipients(Message.RecipientType.BCC, prepareInternetAddress(out.getMAILADDRESS_BCC()));
               	
                    MimeBodyPart mbp1 = new MimeBodyPart();
                	mbp1.setText(out.getBODY());
                	if(out.getBODY_CONTENT_TYPE()!=null && !out.getBODY_CONTENT_TYPE().equals("")){
                       		String charset = MimeUtility.mimeCharset(MimeUtility.getDefaultJavaCharset());
                       		StringBuffer buffer = new StringBuffer();
                       		buffer.append("text/"+out.getBODY_CONTENT_TYPE()+"; charset=");
                       		buffer.append(MimeUtility.quote(charset, "()<>@,;:\\\"\t []/?="));
                       		mbp1.setContent(out.getBODY(), buffer.toString());
                       	}
                	else mbp1.setText(out.getBODY());

                	MimeMultipart mp = new MimeMultipart("related");
                	mp.addBodyPart(mbp1);
                     
                    	for(int i=0;i<out.getATTACHMENTS().size();i++){
		        			try{
		        				MimeBodyPart mbp_att = new MimeBodyPart();
		                     	FileDataSource fds=new FileDataSource(service_adaptPath((String)out.getATTACHMENTS().get(i)));
		                     	mbp_att.setDataHandler(new DataHandler(fds));
		                     	mbp_att.setFileName(service_namePath((String)out.getATTACHMENTS().get(i)));
		                     	mp.addBodyPart(mbp_att);
		        			}catch(Exception e){
		        			}
                    	}	
                    	for(int i=0;i<out.getATTACHMENTS_AS_BYTE().size();i++){
		        			try{
		        				Object[] content_att = (Object[])out.getATTACHMENTS_AS_BYTE().get(i);
		        				MimeBodyPart mbp_att = new MimeBodyPart();
		        				BADataSource fds=new BADataSource((byte[])content_att[0],"application/"+content_att[1]);
		                     	mbp_att.setDataHandler(new DataHandler(fds));
		                     	mbp_att.setFileName((String)content_att[2]);
		                     	mp.addBodyPart(mbp_att);
		        			}catch(Exception e){
		        			}
		        			
                    	}	                    	
                    msg.setContent(mp);
 
                    
                    msg.setSentDate(new java.util.Date());
               	
                	
                Transport.send(msg);
            }
            catch (Exception mex){
            	result&=false;	
            	throw mex;
            } 
 
	return result;
}

private InternetAddress[] prepareInternetAddress(String address_list) throws Exception{
	String sep_def=";";
	if(address_list.indexOf(",")>-1) sep_def=",";
	java.util.StringTokenizer st = new java.util.StringTokenizer(address_list,sep_def);
	Vector sumForAdress = new Vector();
	while(st.hasMoreTokens()) 
		sumForAdress.add(InternetAddress.parse(st.nextToken(), false));
	InternetAddress[] sumAddrs = new InternetAddress[sumForAdress.size()];
	for(int j=0;j<sumForAdress.size();j++)
		sumAddrs[j] = ((InternetAddress[])sumForAdress.get(j))[0];
	return sumAddrs;
}



public static String service_adaptPath(String path){
   	String separatore = _separator();
   	if(separatore==null || separatore.equals("")){
	   	if(path.indexOf("\\")>-1 && path.indexOf("/")>-1){
	   		if(path.indexOf("\\")<path.indexOf("/")){
	   			path=path.replace('/','\\');
	   			separatore="\\";
	 		}else{
	   			path=path.replace('\\','/');
	   			separatore="/";
	   		}	
	   	}else{
	   		if(path.indexOf("\\")==-1)
	   			separatore="/";
	   		else
	   			separatore="\\"; 
	   	}
	}
   	if(separatore.equals("/")) path=path.replace('\\','/');
   	if(separatore.equals("\\")) path=path.replace('/','\\');
	return path;
}

public static boolean service_copyFile(String input, String output){
	java.io.DataInputStream InFile = null;
	java.io.DataOutputStream OutFile = null;
	try {
		long length = new File(input).length();
		InFile  = new java.io.DataInputStream(new BufferedInputStream(new FileInputStream(input)));	
		OutFile = new java.io.DataOutputStream(new BufferedOutputStream(new FileOutputStream(output,false)));
		byte[] readed = new byte[(int)length];
		InFile.read(readed,0,(int)length);
		OutFile.write(readed);	
		InFile.close();
	  	OutFile.close();
	}catch(Exception ex){
		return false;
	}
	return true;
}

public static String service_namePath(String path){
	if(path.equals("")) return path;
   	String separatore = _separator();
   	if(separatore==null || separatore.equals("")){
	   	if(path.indexOf("\\")>-1 && path.indexOf("/")>-1){
	   		if(path.indexOf("\\")<path.indexOf("/")){
	   			path=path.replace('/','\\');
	   			separatore="\\";
	 		}else{
	   			path=path.replace('\\','/');
	   			separatore="/";
	   		}	
	   	}else{
	   		if(path.indexOf("\\")==-1)
	   			separatore="/";
	   		else
	   			separatore="\\"; 
	   	}
	}
   	if(path.lastIndexOf(separatore.charAt(0))>-1 && path.lastIndexOf(separatore.charAt(0))!=path.length()-1)   	
   		return path.substring(path.lastIndexOf(separatore.charAt(0))+1,path.length());
   	else return path;	
}

public static synchronized void service_toZip(String outFilename, String[] filenames, String[] fileLabels) throws Exception{
	if(fileLabels==null || fileLabels.length<filenames.length){
		fileLabels = new String[filenames.length];
		for(int i=0;i<filenames.length;i++)
			fileLabels[i]=service_namePath(filenames[i]);
	}
	byte[] buf = new byte[1024];
	ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
	for (int i=0; i<filenames.length; i++) {
		if(new File(filenames[i]).exists()){
			FileInputStream in = new FileInputStream(filenames[i]);
			out.putNextEntry(new ZipEntry(fileLabels[i]));
			int len;
			while ((len = in.read(buf)) > 0) out.write(buf, 0, len);
			out.closeEntry();
			in.close();
		}	
	}
	out.flush();
	out.finish();
	out.close();
}
}

package neohort.service.ftp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;

public class ftpControlSocket {

     static final String EOL = "\r\n";
     static final int CONTROL_PORT = 21;
     private static final String DEBUG_ARROW = "---> ";
     private static final String PASSWORD_MESSAGE = DEBUG_ARROW + "PASS";
     private boolean debugResponses = false;
     private PrintWriter log = new PrintWriter(System.out);
     private Socket controlSock = null;
     private Writer writer = null;
     private BufferedReader reader = null;

     public ftpControlSocket(String remoteHost, int controlPort, 
                             PrintWriter log, int timeout)
         throws IOException, ftpException {

         setLogStream(log); 

         debugResponses(true);
         controlSock = new Socket(remoteHost, controlPort);
         setTimeout(timeout);
         initStreams();
         validateConnection();

         debugResponses(false);
     }
     public ftpControlSocket(InetAddress remoteAddr, int controlPort, 
                             PrintWriter log, int timeout)
         throws IOException, ftpException {
         
         setLogStream(log);

         debugResponses(true);
         controlSock = new Socket(remoteAddr, controlPort);
         setTimeout(timeout);
         initStreams();
         validateConnection();

         debugResponses(false);
     }
     ftpDataSocket createDataSocket(ftpConnectMode connectMode)
         throws IOException, ftpException {

        if (connectMode == ftpConnectMode.ACTIVE) {
            return new ftpDataSocket(createDataSocketActive());
        }
        else { 
            return new ftpDataSocket(createDataSocketPASV());
        }
     }
     ServerSocket createDataSocketActive()
         throws IOException, ftpException {

        ServerSocket socket = new ServerSocket(0);

        InetAddress localhost =  controlSock.getLocalAddress();

        setDataPort(localhost, (short)socket.getLocalPort());

        return socket;
     }
     Socket createDataSocketPASV()
         throws IOException, ftpException {

         String reply = sendCommand("PASV");
         validateReply(reply, "227");

         int startIP = reply.indexOf('(');
         int endIP = reply.indexOf(')');

         if (startIP < 0 && endIP < 0) {
             startIP = reply.toUpperCase().lastIndexOf("MODE") + 4;
             endIP = reply.length();
         }
                  
         String ipData = reply.substring(startIP+1,endIP);
         int parts[] = new int[6];

         int len = ipData.length();
         int partCount = 0;
         StringBuffer buf = new StringBuffer();

         for (int i = 0; i < len && partCount <= 6; i++) {

             char ch = ipData.charAt(i);
             if (Character.isDigit(ch))
                 buf.append(ch);
             else if (ch != ',') {
                 throw new ftpException("Malformed PASV reply: " + reply);
             }

             if (ch == ',' || i+1 == len) { // at end or at separator
                 try {
                     parts[partCount++] = Integer.parseInt(buf.toString());
                     buf.setLength(0);
                 }
                 catch (NumberFormatException ex) {
                     throw new ftpException("Malformed PASV reply: " + reply);
                 }
             }
         }

         String ipAddress = parts[0] + "."+ parts[1]+ "." +
             parts[2] + "." + parts[3];

         int port = (parts[4] << 8) + parts[5];

         return new Socket(ipAddress, port);
     }
     void debugResponses(boolean on) {
         debugResponses = on;
     }
     String getRemoteHostName() {
         InetAddress addr = controlSock.getInetAddress();
         return addr.getHostName();
     }
     private void initStreams()
         throws IOException {

         InputStream is = controlSock.getInputStream();
         reader = new BufferedReader(new InputStreamReader(is));

         OutputStream os = controlSock.getOutputStream();
         writer = new OutputStreamWriter(os);
     }
     void log(String msg) {
     	 if (debugResponses && log != null) {
     	 	 if (!msg.startsWith(PASSWORD_MESSAGE))
     	     	 log.println(msg);
     	     else
     	         log.println(PASSWORD_MESSAGE + " ********");
     	 }
     }
     public void logout()
         throws IOException {

         if (log != null) {
         	log.flush(); 
         	log = null;
         }

         IOException ex = null;
         try {
             writer.close();
         }
         catch (IOException e) {
             ex = e;
         }
         try {
             reader.close();
         }
         catch (IOException e) {
             ex = e;
         }
         try {
             controlSock.close();
         }
         catch (IOException e) {
             ex = e;
         }
         if (ex != null)
             throw ex;
     }
     String readReply()
         throws IOException {

         String firstLine = reader.readLine();
         if (firstLine == null || firstLine.length() == 0)
             throw new IOException("Unexpected null reply received");

         StringBuffer reply = new StringBuffer(firstLine);

		 log(reply.toString());
 
         String replyCode = reply.toString().substring(0, 3);

         if (reply.charAt(3) == '-') {

             boolean complete = false;
             while (!complete) {
                 String line = reader.readLine();
                 if (line == null)
                     throw new IOException("Unexpected null reply received");

			     log(line);

                 if (line.length() > 3 &&
                     line.substring(0, 3).equals(replyCode) &&
                     line.charAt(3) == ' ') {
                     reply.append(line.substring(3));
                     complete = true;
                 }
                 else { 
                     reply.append(" ");
                     reply.append(line);
                 }
             } 
         } 
         return reply.toString();
     }
     String sendCommand(String command)
         throws IOException {

    	 log(DEBUG_ARROW + command);

         writer.write(command + EOL);
         writer.flush();

         return readReply();
     }
    private void setDataPort(InetAddress host, short portNo)
        throws IOException, ftpException {

        byte[] hostBytes = host.getAddress();
        byte[] portBytes = toByteArray(portNo);

        String cmd = new StringBuffer ("PORT ")
            .append (toUnsignedShort (hostBytes[0])) .append (",")
            .append (toUnsignedShort (hostBytes[1])) .append (",")
            .append (toUnsignedShort (hostBytes[2])) .append (",")
            .append (toUnsignedShort (hostBytes[3])) .append (",")
            .append (toUnsignedShort (portBytes[0])) .append (",")
            .append (toUnsignedShort (portBytes[1])) .toString ();

        String reply = sendCommand(cmd);
        validateReply(reply, "200");
     }
     void setLogStream(PrintWriter log) {
         if (log != null)
             this.log = log;
         else
			 debugResponses = false;
     }
    void setTimeout(int millis)
        throws IOException {

        if (controlSock == null)
            throw new IllegalStateException(
                        "Failed to set timeout - no control socket");

        controlSock.setSoTimeout(millis);
    }
    protected byte[] toByteArray (short value) {

        byte[] bytes = new byte[2];
        bytes[0] = (byte) (value >> 8);     // bits 1- 8
        bytes[1] = (byte) (value & 0x00FF); // bits 9-16
        return bytes;
    }
    private short toUnsignedShort(byte value) {
        return ( value < 0 )
            ? (short) (value + 256)
            : (short) value;
     }
     private void validateConnection()
         throws IOException, ftpException {

         String reply = readReply();
         validateReply(reply, "220");
     }
     ftpReply validateReply(String reply, String[] expectedReplyCodes)
         throws IOException, ftpException {

         String replyCode = reply.substring(0, 3);
         String replyText = reply.substring(4);

         ftpReply replyObj = new ftpReply(replyCode, replyText);

         for (int i = 0; i < expectedReplyCodes.length; i++)
             if (replyCode.equals(expectedReplyCodes[i]))
                 return replyObj;

         throw new ftpException(replyText, replyCode);
     }
     ftpReply validateReply(String reply, String expectedReplyCode)
         throws IOException, ftpException {

         String replyCode = reply.substring(0, 3);
         String replyText = reply.substring(4);
         ftpReply replyObj = new ftpReply(replyCode, replyText);
         
         if (replyCode.equals(expectedReplyCode)) 
             return replyObj;

         throw new ftpException(replyText, replyCode);         
     }
}

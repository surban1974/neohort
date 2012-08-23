package neohort.service.ftp;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;

import java.text.SimpleDateFormat;
import java.text.ParsePosition;

import java.net.InetAddress;
import java.util.Date;
import java.util.Vector;
import java.util.Properties;

public class ftpClient {
    private SimpleDateFormat tsFormat =
        new SimpleDateFormat("yyyyMMddHHmmss");

    private ftpControlSocket control = null;
    private ftpDataSocket data = null;
    private int timeout = 0;
    private ftpTransferType transferType = ftpTransferType.ASCII;
    private ftpConnectMode connectMode = ftpConnectMode.PASV;
    private ftpReply lastValidReply;

    public ftpClient(String remoteHost)
        throws IOException, ftpException {

        control = new ftpControlSocket(remoteHost, 
                                       ftpControlSocket.CONTROL_PORT,
                                       null, 0);
    }
    public ftpClient(String remoteHost, int controlPort)
        throws IOException, ftpException {

        control = new ftpControlSocket(remoteHost, controlPort, null, 0);
    }
    public ftpClient(String remoteHost, int controlPort, 
                     PrintWriter log, int timeout)
        throws IOException, ftpException {

        control = new ftpControlSocket(remoteHost, controlPort,
                                       log, timeout);
    }
    public ftpClient(String remoteHost, PrintWriter log, int timeout)
        throws IOException, ftpException {

        control = new ftpControlSocket(remoteHost, 
                                       ftpControlSocket.CONTROL_PORT,
                                       log, timeout);
    }
    public ftpClient(InetAddress remoteAddr)
        throws IOException, ftpException {

        control = new ftpControlSocket(remoteAddr, 
                                       ftpControlSocket.CONTROL_PORT,
                                       null, 0);
    }
    public ftpClient(InetAddress remoteAddr, int controlPort)
        throws IOException, ftpException {

        control = new ftpControlSocket(remoteAddr, controlPort,
                                       null, 0);
    }
    public ftpClient(InetAddress remoteAddr, int controlPort, 
                     PrintWriter log, int timeout)
        throws IOException, ftpException {

        control = new ftpControlSocket(remoteAddr, controlPort, 
                                       log, timeout);
    }
    public ftpClient(InetAddress remoteAddr, PrintWriter log, 
                     int timeout)
        throws IOException, ftpException {

        control = new ftpControlSocket(remoteAddr, 
                                       ftpControlSocket.CONTROL_PORT,
                                       log, timeout);
    }
    public void chdir(String dir)
        throws IOException, ftpException {

        String reply = control.sendCommand("CWD " + dir);
        lastValidReply = control.validateReply(reply, "250");
    }
    public void debugResponses(boolean on) {
        control.debugResponses(on);
    }
    public void delete(String remoteFile)
        throws IOException, ftpException {

        String reply = control.sendCommand("DELE " + remoteFile);
        lastValidReply = control.validateReply(reply, "250");
    }
    public String[] dir()
        throws IOException, ftpException {

        return dir(null, false);
    }
    public String[] dir(String dirname)
        throws IOException, ftpException {

        return dir(dirname, false);
    }
    public String[] dir(String dirname, boolean full)
        throws IOException, ftpException {

        data = control.createDataSocket(connectMode);
        data.setTimeout(timeout);

        String command = full ? "LIST ":"NLST ";
        if (dirname != null)
            command += dirname;

        command = command.trim();
        String reply = control.sendCommand(command);

        String[] validCodes1 = {"125", "150", "450", "550"};
        lastValidReply = control.validateReply(reply, validCodes1);  

        String[] result = new String[0];

        String replyCode = lastValidReply.getReplyCode();
        if (!replyCode.equals("450") && !replyCode.equals("550")) {
            LineNumberReader in =
                new LineNumberReader(
                     new InputStreamReader(data.getInputStream()));

            Vector lines = new Vector();    
            String line = null;
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }        
            try {
                in.close();
                data.close();
            }
            catch (IOException ignore) {}
                
            String[] validCodes2 = {"226", "250"};
            reply = control.readReply();
            lastValidReply = control.validateReply(reply, validCodes2);

            if (!lines.isEmpty())
                result = (String[])lines.toArray(result);
        }
        return result;
    }
    public void get(OutputStream destStream, String remoteFile)
        throws IOException, ftpException {

        if (getType() == ftpTransferType.ASCII) {
            getASCII(destStream, remoteFile);
        }
        else {
            getBinary(destStream, remoteFile);
        }
        validateTransfer();
    }
    public byte[] get(String remoteFile)
        throws IOException, ftpException {

        initGet(remoteFile);

        BufferedInputStream in =
            new BufferedInputStream(
                new DataInputStream(data.getInputStream()));

        data.setTimeout(timeout);

        int chunksize = 4096;
        byte [] chunk = new byte[chunksize];
        byte [] resultBuf = null;
        ByteArrayOutputStream temp =
            new ByteArrayOutputStream(chunksize); // temp swap buffer
        int count;

        while ((count = in.read(chunk, 0, chunksize)) >= 0) {
            temp.write(chunk, 0, count);
        }
        temp.close();

        resultBuf = temp.toByteArray();

        try {
            in.close();
            data.close();
        }
        catch (IOException ignore) {}
        
        validateTransfer();

        return resultBuf;
    }
    public void get(String localPath, String remoteFile)
        throws IOException, ftpException {

        if (getType() == ftpTransferType.ASCII) {
            getASCII(localPath, remoteFile);
        }
        else {
            getBinary(localPath, remoteFile);
        }
        validateTransfer();
    }
    private void getASCII(OutputStream destStream, String remoteFile)
        throws IOException, ftpException {

        initGet(remoteFile);

        BufferedWriter out =
            new BufferedWriter(
                new OutputStreamWriter(destStream));

        LineNumberReader in =
            new LineNumberReader(
                new InputStreamReader(data.getInputStream()));

        data.setTimeout(timeout);

        IOException storedEx = null;
        String line = null;
        try {
            while ((line = in.readLine()) != null) {
                out.write(line, 0, line.length());
                out.newLine();
            }
        }
        catch (IOException ex) {
            storedEx = ex;
        }
        finally {
            out.close();
        }

        try {
            in.close();
            data.close();
        }
        catch (IOException ignore) {}

        if (storedEx != null)
            throw storedEx;
    }
    private void getASCII(String localPath, String remoteFile)
        throws IOException, ftpException {

        initGet(remoteFile);

        File localFile = new File(localPath);

        BufferedWriter out =
            new BufferedWriter(
                new FileWriter(localPath));

        LineNumberReader in =
            new LineNumberReader(
                new InputStreamReader(data.getInputStream()));

        data.setTimeout(timeout);


        IOException storedEx = null;
        String line = null;
        try {
            while ((line = in.readLine()) != null) {
                out.write(line, 0, line.length());
                out.newLine();
            }
        }
        catch (IOException ex) {
            storedEx = ex;
            localFile.delete();
        }
        finally {
            out.close();
        }

        try {
            in.close();
            data.close();
        }
        catch (IOException ignore) {}

        if (storedEx != null)
            throw storedEx;
    }
    private void getBinary(OutputStream destStream, String remoteFile)
        throws IOException, ftpException {

        initGet(remoteFile);

        BufferedOutputStream out =
            new BufferedOutputStream(destStream);

        BufferedInputStream in =
            new BufferedInputStream(
                new DataInputStream(data.getInputStream()));

        data.setTimeout(timeout);

		long size = 0;
        int chunksize = 4096;
        byte [] chunk = new byte[chunksize];
        int count;
        IOException storedEx = null;

        try {
            while ((count = in.read(chunk, 0, chunksize)) >= 0) {
                out.write(chunk, 0, count);
				size += count;
            }
        }
        catch (IOException ex) {
            storedEx = ex;
        }
        finally {
            out.close();
        }

        try {
            in.close();
            data.close();
        }
        catch (IOException ignore) {}

        if (storedEx != null)
            throw storedEx;
            
		control.log("Transferred " + size + " bytes from remote host");            
    }
    private void getBinary(String localPath, String remoteFile)
        throws IOException, ftpException {
        initGet(remoteFile);

        File localFile = new File(localPath);

        BufferedOutputStream out =
            new BufferedOutputStream(
                new FileOutputStream(localPath, false));

        BufferedInputStream in =
            new BufferedInputStream(
                new DataInputStream(data.getInputStream()));

        data.setTimeout(timeout);

        long size = 0;
        int chunksize = 4096;
        byte [] chunk = new byte[chunksize];
        int count;
        IOException storedEx = null;

        try {
            while ((count = in.read(chunk, 0, chunksize)) >= 0) {
                out.write(chunk, 0, count);
                size += count;
            }
        }
        catch (IOException ex) {
            storedEx = ex;
            localFile.delete();
        }
        finally {
            out.close();
        }

        try {
            in.close();
            data.close();
        }
        catch (IOException ignore) {}

        if (storedEx != null)
            throw storedEx;
            
    	control.log("Transferred " + size + " bytes from remote host");
    }
    public ftpReply getLastValidReply() {
        return lastValidReply;
    }
    String getRemoteHostName() {
        return control.getRemoteHostName();
    }
    public ftpTransferType getType() {
        return transferType;
    }
    public String help(String command)
        throws IOException, ftpException {

        String reply = control.sendCommand("HELP " + command);
        String[] validCodes = {"211", "214"};
        lastValidReply = control.validateReply(reply, validCodes);
        return lastValidReply.getReplyText();
    }
    private void initGet(String remoteFile)
        throws IOException, ftpException {

        data = control.createDataSocket(connectMode);
        data.setTimeout(timeout);

        String reply = control.sendCommand("RETR " + remoteFile);

        String[] validCodes1 = {"125", "150"};
        lastValidReply = control.validateReply(reply, validCodes1);
    }
    private void initPut(String remoteFile, boolean append)
        throws IOException, ftpException {

        data = control.createDataSocket(connectMode);
        data.setTimeout(timeout);

        String cmd = append ? "APPE " : "STOR ";
        String reply = control.sendCommand(cmd + remoteFile);

        String[] validCodes = {"125", "150"};
        lastValidReply = control.validateReply(reply, validCodes);
    }
    public static void initSOCKS(String port, String host) {
        Properties props = System.getProperties();
        props.put("socksProxyPort", port);
        props.put("socksProxyHost", host);
        System.setProperties(props);
    }
    public static void initSOCKSAuthentication(String username,
                                               String password) {
        Properties props = System.getProperties();
        props.put("java.net.socks.username", username);
        props.put("java.net.socks.password", password);
        System.setProperties(props);
    }
    public String list(String dirname)
        throws IOException, ftpException {

        return list(dirname, false);
    }
    public String list(String dirname, boolean full)
        throws IOException, ftpException {

        String[] list = dir(dirname, full);

        StringBuffer result = new StringBuffer();
        String sep = System.getProperty("line.separator");

        for (int i = 0; i < list.length; i++) {
            result.append(list[i]);
            result.append(sep);
        }

        return result.toString();
    }
    public void login(String user, String password)
        throws IOException, ftpException {

        String response = control.sendCommand("USER " + user);
        lastValidReply = control.validateReply(response, "331");
        response = control.sendCommand("PASS " + password);
        lastValidReply = control.validateReply(response, "230");
    }
    public void mkdir(String dir)
        throws IOException, ftpException {

        String reply = control.sendCommand("MKD " + dir);
        lastValidReply = control.validateReply(reply, "257");
    }
    public Date modtime(String remoteFile)
        throws IOException, ftpException {

        String reply = control.sendCommand("MDTM " + remoteFile);
        lastValidReply = control.validateReply(reply, "213");

        Date ts = tsFormat.parse(lastValidReply.getReplyText(),
                                 new ParsePosition(0));
        return ts;
    }
    public void password(String password)
        throws IOException, ftpException {

        String reply = control.sendCommand("PASS " + password);

        // we allow for a site with no passwords (202)
        String[] validCodes = {"230", "202"};
        lastValidReply = control.validateReply(reply, validCodes);
    }
    public void put(byte[] bytes, String remoteFile)
        throws IOException, ftpException {

        put(bytes, remoteFile, false);
    }
    public void put(byte[] bytes, String remoteFile, boolean append)
        throws IOException, ftpException {

        initPut(remoteFile, append);

        BufferedOutputStream out =
            new BufferedOutputStream(
                new DataOutputStream(data.getOutputStream()));

        out.write(bytes, 0, bytes.length);

        out.flush();
        out.close();

        try {
            data.close();
        }
        catch (IOException ignore) {}

        validateTransfer();
    }
    public void put(InputStream srcStream, String remoteFile)
        throws IOException, ftpException {

        put(srcStream, remoteFile, false);
    }
    public void put(InputStream srcStream, String remoteFile,
                    boolean append)
        throws IOException, ftpException {

        if (getType() == ftpTransferType.ASCII) {
            putASCII(srcStream, remoteFile, append);
        }
        else {
            putBinary(srcStream, remoteFile, append);
        }
        validateTransfer();
    }
    public void put(String localPath, String remoteFile)
        throws IOException, ftpException {

        put(localPath, remoteFile, false);
    }
    public void put(String localPath, String remoteFile,
                    boolean append)
        throws IOException, ftpException {

        if (getType() == ftpTransferType.ASCII) {
            putASCII(localPath, remoteFile, append);
        }
        else {
            putBinary(localPath, remoteFile, append);
        }
        validateTransfer();
     }
    private void putASCII(InputStream srcStream, String remoteFile,
                          boolean append)
        throws IOException, ftpException {

        LineNumberReader in
            = new LineNumberReader(new InputStreamReader(srcStream));

        initPut(remoteFile, append);

        BufferedWriter out =
            new BufferedWriter(
                new OutputStreamWriter(data.getOutputStream()));

        String line = null;
        while ((line = in.readLine()) != null) {
            out.write(line, 0, line.length());
            out.write(ftpControlSocket.EOL, 0, ftpControlSocket.EOL.length());
        }
        in.close();
        out.flush();
        out.close();

        try {
            data.close();
        }
        catch (IOException ignore) {}
    }
private void putASCII(String localPath, String remoteFile, boolean append)
    throws IOException, ftpException {

    InputStream srcStream = new FileInputStream(localPath);
    putASCII(srcStream, remoteFile, append);
}
    private void putBinary(InputStream srcStream, String remoteFile,
                           boolean append)
        throws IOException, ftpException {

        BufferedInputStream in =
            new BufferedInputStream(srcStream);

        initPut(remoteFile, append);

        BufferedOutputStream out =
            new BufferedOutputStream(
                new DataOutputStream(data.getOutputStream()));

        byte[] buf = new byte[512];

        long size = 0;
        int count = 0;
        while ((count = in.read(buf)) > 0) {
            out.write(buf, 0, count);
            size += count;
        }
        
        in.close();

        out.flush();
        out.close();

        try {
            data.close();
        }
        catch (IOException ignore) {}
        
		control.log("Transferred " + size + " bytes to remote host");
    }
    private void putBinary(String localPath, String remoteFile,
                           boolean append)
        throws IOException, ftpException {
        InputStream srcStream = new FileInputStream(localPath);
        putBinary(srcStream, remoteFile, append);
    }
    public String pwd()
        throws IOException, ftpException {

        String reply = control.sendCommand("PWD");
        lastValidReply = control.validateReply(reply, "257");

        String text = lastValidReply.getReplyText();
        int start = text.indexOf('"');
        int end = text.lastIndexOf('"');
        if (start >= 0 && end > start)
            return text.substring(start+1, end);
        else
            return text;
    }
    public void quit()
        throws IOException, ftpException {

        try {
            String reply = control.sendCommand("QUIT");
            String[] validCodes = {"221", "226"};
            lastValidReply = control.validateReply(reply, validCodes);
        }
        finally {
            control.logout();
            control = null;
        }
    }
    public void quote(String command, String[] validCodes)
        throws IOException, ftpException {

        String reply = control.sendCommand(command);

        if (validCodes != null && validCodes.length > 0)
            lastValidReply = control.validateReply(reply, validCodes);
    }
    public void rename(String from, String to)
        throws IOException, ftpException {

        String reply = control.sendCommand("RNFR " + from);
        lastValidReply = control.validateReply(reply, "350");

        reply = control.sendCommand("RNTO " + to);
        lastValidReply = control.validateReply(reply, "250");
    }
    public void rmdir(String dir)
        throws IOException, ftpException {

        String reply = control.sendCommand("RMD " + dir);
        String[] validCodes = {"250", "257"};
        lastValidReply = control.validateReply(reply, validCodes);
    }
    public void setConnectMode(ftpConnectMode mode) {
        connectMode = mode;
    }
     public void setLogStream(PrintWriter log) {
         control.setLogStream(log);         
     }
    public void setTimeout(int millis)
        throws IOException {

        this.timeout = millis;
        control.setTimeout(millis);
    }
    public void setType(ftpTransferType type)
        throws IOException, ftpException {

        String typeStr = ftpTransferType.ASCII_CHAR;
        if (type.equals(ftpTransferType.BINARY))
            typeStr = ftpTransferType.BINARY_CHAR;

        String reply = control.sendCommand("TYPE " + typeStr);
        lastValidReply = control.validateReply(reply, "200");

        transferType = type;
    }
    public boolean site(String command)
        throws IOException, ftpException {

        String reply = control.sendCommand("SITE " + command);

        String[] validCodes = {"200", "202", "502"};
        lastValidReply = control.validateReply(reply, validCodes);

        if (reply.substring(0, 3).equals("200"))
            return true;
        else
            return false;
    }
    public String system()
        throws IOException, ftpException {

        String reply = control.sendCommand("SYST");
        lastValidReply = control.validateReply(reply, "215");
        return lastValidReply.getReplyText();
    }
    public void user(String user)
        throws IOException, ftpException {

        String reply = control.sendCommand("USER " + user);

        String[] validCodes = {"230", "331"};
        lastValidReply = control.validateReply(reply, validCodes);
    }
    private void validateTransfer()
        throws IOException, ftpException {

        String[] validCodes = {"226", "250"};
        String reply = control.readReply();
        lastValidReply = control.validateReply(reply, validCodes);
    }
}

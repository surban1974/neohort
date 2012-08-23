package neohort.service.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.net.ServerSocket;

public class ftpDataSocket {
    private ServerSocket activeSocket = null;
    private Socket passiveSocket = null;

    ftpDataSocket(ServerSocket s) {
         activeSocket = s;
    }
    ftpDataSocket(Socket s) {
         passiveSocket = s;
    }
    void close() throws IOException {

        if (passiveSocket != null)
            passiveSocket.close();
        if (activeSocket != null)
            activeSocket.close();
    }
    InputStream getInputStream() throws IOException {

        if (passiveSocket != null) {
            return passiveSocket.getInputStream();
        } else {
            passiveSocket = activeSocket.accept();
            return passiveSocket.getInputStream ();
        }
    }
    OutputStream getOutputStream() throws IOException {

        if (passiveSocket != null) {
            return passiveSocket.getOutputStream();
        }
        else {
            passiveSocket = activeSocket.accept();
            return passiveSocket.getOutputStream ();
        }
    }
    void setTimeout(int millis)
        throws IOException {

        if (passiveSocket != null)
            passiveSocket.setSoTimeout(millis);
        else if (activeSocket != null)
            activeSocket.setSoTimeout(millis);
    }
}

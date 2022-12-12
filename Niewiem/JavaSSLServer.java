

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;


public class JavaSSLServer {

static final int port = 8000;

public static void main(String[] args) {

    System.setProperty("javax.net.ssl.keyStore","123Server1KeyStore.jks");
    System.setProperty("javax.net.ssl.keyStorePassword","pass12");
    SSLServerSocketFactory sslServerSocketFactory = 
            (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();

    try {


        SSLServerSocket sslServerSocket = 
                (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);
	sslServerSocket.setEnabledProtocols(new String[] {"TLSv1"});
        System.out.println("SSL uruchomione");
        System.out.println(sslServerSocket.toString());

        Socket socket = sslServerSocket.accept();
        System.out.println("Accepted");

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = 
                new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
            String line;
            while((line = bufferedReader.readLine()) != null){
                System.out.println(line);
                out.println(line);
            }
        
        System.out.println("Closed");

    } catch (IOException ex) {
        Logger.getLogger(JavaSSLServer.class.getName())
                .log(Level.SEVERE, null, ex);
    }
}}
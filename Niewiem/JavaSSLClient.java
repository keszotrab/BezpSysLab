
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;


public class JavaSSLClient {

static final int port = 8000;

public static void main(String[] args) {
    System.setProperty("javax.net.ssl.trustStore", "Server1CertStore.jks");
    System.setProperty("javax.net.ssl.trustStorePassword", "password"); 
    SSLSocketFactory sslSocketFactory = 
            (SSLSocketFactory)SSLSocketFactory.getDefault();
    try {
        SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket("127.0.0.1", port);
	socket.setEnabledProtocols(new String[] {"TLSv1"});
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = 
                new BufferedReader(
                        new InputStreamReader(socket.getInputStream())); 
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("Wpisz tekst:");
                String inputLine = scanner.nextLine();
                if(inputLine.equals("q")){
                    break;
                }

                out.println(inputLine);
                System.out.println(bufferedReader.readLine());
            }
        

    } catch (IOException ex) {
        Logger.getLogger(JavaSSLClient.class.getName())
                .log(Level.SEVERE, null, ex);
    }

}

}


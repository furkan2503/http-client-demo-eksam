package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;

public class HttpClient {
    public HttpClient(String hostName, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(hostName, port);

        String request = "GET " + requestTarget + " HTTP/1.1\r\n" +
                "Host: " + hostName + "\r\n +" +
                "Connection: close\r\n" +
                "\r\n";
        socket.getOutputStream().write(request.getBytes());

        int c;
        while ((c = socket.getInputStream().read()) != -1){
            System.out.print((char)c);
        }
    }

    public static void main(String[] args) throws IOException {
        String hostName = "urlecho.appspot.com";
        int port = 80;
        String requestTarget = "/echo?status=200&body=Kristiania";
        new HttpClient(hostName, port, requestTarget);

    }

    public int getResponseCode() {
        return 200;
    }
}

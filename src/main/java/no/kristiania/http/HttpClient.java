package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;

public class HttpClient {
    public HttpClient(String hostName, int port, String requestTarget) {

    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("urlecho.appspot.com", 80);

        String request = "https://urleco.appspot.com/echo?status=200&body=Pause%20til%2014:25&hallo=there HTTP/1.1\r\n" +
                "Host: urlecho.appspot.com\r\n +" +
                "Connection: close\r\n" +
                "\r\n";
        socket.getOutputStream().write(request.getBytes());

        int c;
        while ((c = socket.getInputStream().read()) != -1){
            System.out.print((char)c);
        }
    }

    public int getResponseCode() {
        return 0;
    }
}

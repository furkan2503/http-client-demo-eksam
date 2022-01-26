package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;

public class HttpClient {

    private final int responseCode;

    public HttpClient(String hostName, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(hostName, port);

        String request = "GET " + requestTarget + " HTTP/1.1\r\n" +
                "Host: " + hostName + "\r\n +" +
                "Connection: close\r\n" +
                "\r\n";
        socket.getOutputStream().write(request.getBytes());

        StringBuilder line = new StringBuilder();
        int c;
        while ((c = socket.getInputStream().read()) != -1){
            if (c == '\n') {
                break;
            }
            line.append((char)c);
        }
        String[] responseLineParts = line.toString().split("");
        responseCode = Integer.parseInt(responseLineParts[1]);
    }

    public static void main(String[] args) throws IOException {
        String hostName = "urlecho.appspot.com";
        int port = 80;
        String requestTarget = "/echo?status=200&body=Kristiania";
        new HttpClient(hostName, port, requestTarget);

    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseHeader(String headerName) {
        return null;
    }
}

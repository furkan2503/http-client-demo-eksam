package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {

    private final int responseCode;
    private final Map<String, String> responseHeaders = new HashMap<>();

    public HttpClient(String hostName, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(hostName, port);

        String request = "GET " + requestTarget + " HTTP/1.1\r\n" +
                "Host: " + hostName + "\r\n +" +
                "Connection: close\r\n" +
                "\r\n";
        socket.getOutputStream().write(request.getBytes());

        String responseLine = readLine(socket);
        String[] responseLineParts = responseLine.split("");
        responseCode = Integer.parseInt(responseLineParts[1]);

        String headerLine;
        while (!(headerLine = readLine(socket)).isEmpty()) {
            int colonPos = headerLine.indexOf(':');
            String fieldName = headerLine.substring(0, colonPos);
            String fieldValue = headerLine.substring(colonPos+1);
            responseHeaders.put(fieldName, fieldValue);
        }
    }

    private String readLine(Socket socket) throws IOException {
        StringBuilder line = new StringBuilder();
        int c;
        while ((c = socket.getInputStream().read()) != -1){
            if (c == '\n') {
                break;
            }
            line.append((char)c);
        }
        return line.toString();
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
        return responseHeaders.get(headerName);
    }
}

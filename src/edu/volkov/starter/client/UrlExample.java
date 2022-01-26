package edu.volkov.starter.client;

import java.io.IOException;
import java.net.URL;

public class UrlExample {

    public static void main(String[] args) throws IOException {
        var url = new URL("file:C:/Users/Andrey/IdeaProjects/http-servlet-starter/src/edu/volkov/starter/socket/DatagramRunner.java");
        var connection = url.openConnection();

        System.out.println(new String(connection.getInputStream().readAllBytes()));
    }

    private static void checkGoogle() throws IOException {
        var url = new URL("https://google.com");
        var urlConnection = url.openConnection();
        urlConnection.setDoOutput(true);
        try (var outS = urlConnection.getOutputStream()) {
            outS.write("Hello!!!".getBytes());
        }
        System.out.println();
    }
}

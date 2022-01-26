package server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static java.net.http.HttpRequest.BodyPublishers.ofFile;
import static java.net.http.HttpResponse.BodyHandlers.ofString;

public class HttpClientRunner {

    public static void main(String[] args) throws IOException, InterruptedException {
        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080"))
                .header("content-type", "application/json")
                .POST(ofFile(Path.of("src/resources", "my.json")))
                .build();

        HttpResponse<String> response_1 = client.send(request, ofString());
        HttpResponse<String> response_2 = client.send(request, ofString());
        HttpResponse<String> response_3 = client.send(request, ofString());


        System.out.println(response_1.headers());
        System.out.println(response_1.body());

        System.out.println(response_2.headers());

        System.out.println(response_2.body());

        System.out.println(response_3.headers());
        System.out.println(response_3.body());
    }
}

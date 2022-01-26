package server;

public class HttpServerRunner {

    public static void main(String[] args) {
        new HttpServer(8080, 100).run();
    }
}

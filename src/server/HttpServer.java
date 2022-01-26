package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private final ExecutorService pool;
    private final int port;
    private boolean stoped;

    public HttpServer(int port, int poolSize) {
        this.port = port;
        this.pool = Executors.newFixedThreadPool(poolSize);
    }

    public void run() {
        try {
            var server = new ServerSocket(port);
            while (!stoped) {
                var socket = server.accept();
                System.out.println("Socket accepted");
                pool.submit(() -> processSocket(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try (socket;
             var is = new DataInputStream(socket.getInputStream());
             var os = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("Request: " + new String(is.readNBytes(400)));
            Thread.sleep(10000);
            byte[] body = Files.readAllBytes(Path.of("src/resources", "example.html"));
            var headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length);
            os.write(headers.getBytes());
            os.write(System.lineSeparator().getBytes());
            os.write(body);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }
}

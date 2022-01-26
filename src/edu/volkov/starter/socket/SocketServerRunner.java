package edu.volkov.starter.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServerRunner {

    public static void main(String[] args) throws IOException {
        try (var serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in);) {
            String request = inputStream.readUTF();
            while (!"stop".equals(request)) {
                System.out.println("Client request " + request);
                String response = scanner.nextLine();
                outputStream.writeUTF(response);
                request = inputStream.readUTF();
            }
        }
    }
}

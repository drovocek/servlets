package edu.volkov.starter.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramServerRunner {

    public static void main(String[] args) throws IOException {
        try (var server = new DatagramSocket(7777);) {
            var buffer = new byte[512];
            var packet = new DatagramPacket(buffer, buffer.length);
            server.receive(packet);
            byte[] data = packet.getData();
            System.out.println(new String(data));
        }
    }
}

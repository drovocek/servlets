package edu.volkov.starter.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class DatagramRunner {

    public static void main(String[] args) throws SocketException, IOException {
        var inetAddress = InetAddress.getByName("localhost");
        try (var datagramSocket = new DatagramSocket(8080)) {
            var bytes = "hello from".getBytes();
            var packet = new DatagramPacket(bytes, bytes.length, inetAddress,7777);
            datagramSocket.send(packet);
        }
    }
}

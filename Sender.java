import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Sender extends Thread{
    InetAddress group;
    int port;
    MulticastSocket socket;
    InitAddress setAddr;
    DatagramPacket packet;
    int sleepTime = 1000;

    public Sender(int port, InetAddress group, InitAddress setAddr) throws IOException {
        this.group = group;
        this.port = port;
        this.setAddr = setAddr;
        socket = new MulticastSocket();
        String message = "Hi!";
        packet = new DatagramPacket(message.getBytes(), message.length(), group, port);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

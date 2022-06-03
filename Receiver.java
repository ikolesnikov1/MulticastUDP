import java.io.IOException;
import java.net.*;

public class Receiver extends Thread {
    InetAddress group;
    int port;
    MulticastSocket socket;
    InitAddress address;

    public Receiver(int port, InetAddress group, InitAddress address) throws IOException {
        this.group = group;
        this.port = port;
        this.address = address;
        socket = new MulticastSocket(port);
        socket.joinGroup(new InetSocketAddress(group, port), NetworkInterface.getByInetAddress(group));
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            address.add(packet.getSocketAddress(), System.currentTimeMillis());
        }
    }
}

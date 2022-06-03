import java.io.IOException;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) {
        String ipAddress = args[0];
        int port = 8888;
        InetAddress group;
        InitAddress address = new InitAddress();

        try {
            group = InetAddress.getByName(ipAddress);
            Sender sender = new Sender(port, group, address);
            sender.start();
            Receiver receiver = new Receiver(port, group, address);
            receiver.start();
            Checker checker = new Checker(address);
            checker.start();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}

import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InitAddress {
    private final ConcurrentHashMap<SocketAddress, Long> addr;
    public InitAddress() {
        addr = new ConcurrentHashMap<>();
    }

    public void add(SocketAddress addr, long time) {
        if(this.addr.containsKey(addr))
            this.addr.replace(addr, time);

        else {
            this.addr.put(addr, time);
            print();
        }
    }

    public void print() {
        for(SocketAddress socketAddress : addr.keySet())
            System.out.println(socketAddress);
        System.out.println("--------------------");
    }

    public void check() {
        boolean flag = true;
        Iterator<Map.Entry<SocketAddress, Long>> iterator = addr.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<SocketAddress, Long> entry = iterator.next();
            long timeout = 4000;
            if (timeout < (System.currentTimeMillis() - entry.getValue())) {
                iterator.remove();
                flag = false;
            }
        }
        if (!flag) {
            print();
        }
    }
}

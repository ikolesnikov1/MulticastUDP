public class Checker extends Thread{
    InitAddress addr;
    int sleepTime = 1000;

    public Checker(InitAddress addr) {
        this.addr = addr;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            addr.check();
        }
    }
}

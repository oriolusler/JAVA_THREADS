package Exercise_02_PingPong_A;

import java.util.concurrent.TimeUnit;

public class PingPong {

    public static void main(String[] args) throws InterruptedException {
        Ping ping = new Ping(500);
        Pong pong = new Pong(500);

        ping.start();
        pong.start();


        Thread.sleep(5000);
        ping.stop();
        pong.stop();
    }

}


class Ping extends Thread {
    private long timeToSleep;

    public Ping(long timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("PING");
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Pong extends Thread {
    private long timeToSleep;

    public Pong(long timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("    PONG");
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
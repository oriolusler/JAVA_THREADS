package Exercise_02_PingPong_BA;

public class PingPong_Sync_A {

    public static void main(String[] args) {
        Ping ping = new Ping();
        Pong pong = new Pong();

        ping.setCompanion(pong);
        pong.setCompanion(ping);

        pong.start();
        ping.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }

        ping.stop();
        pong.stop();
    }

}

class Ping extends Thread {

    public volatile boolean canPrint = true;

    private Pong companion;

    public void setCompanion(Pong companion) {
        this.companion = companion;
    }

    public void setCanPrint(boolean canPrint) {
        this.canPrint = canPrint;
    }

    @Override
    public void run() {
        while (true) {
            while (!canPrint) {
                /*Busy waiting
                 * Best option:
                 *   - Sleep for a short time
                 *   - Yield the cpu ot another thread*/
            }
            System.out.println("PING");
            canPrint = false;
            companion.setCanPrint(true);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    /*COMPLETE*/

}

class Pong extends Thread {

    public volatile boolean canPrint = false;

    private Ping companion;

    public void setCompanion(Ping companion) {
        this.companion = companion;
    }

    public void setCanPrint(boolean canPrint) {
        this.canPrint = canPrint;
    }

    @Override
    public void run() {
        while (true) {
            while (!canPrint) {
                /*busy wait*/
            }
            System.out.println("    PONG");
            canPrint = false;
            companion.setCanPrint(true);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* COMPLETE*/

    }
}

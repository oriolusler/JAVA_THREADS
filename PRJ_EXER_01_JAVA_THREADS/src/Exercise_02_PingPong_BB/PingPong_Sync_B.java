package Exercise_02_PingPong_BB;

public class PingPong_Sync_B {

    public static void main(String[] args) {
        SharedObject shared = new SharedObject();

        /*COMPLETE*/
        Ping ping = new Ping(shared);
        Pong pong = new Pong(shared);

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

class SharedObject {
    private volatile int whoCanPrint = 1;

    public int getWhoCanPrint() {
        return this.whoCanPrint;
    }

    public void setWhoCanPrint(int wcp) {
        this.whoCanPrint = wcp;
    }
}

class Ping extends Thread {

    private SharedObject shared;

    /* COMPLETE*/
    public Ping(SharedObject sharedObject) {
        this.shared = sharedObject;
    }

    public void run() {
        while (true) {
            while (shared.getWhoCanPrint() != 1) {/*Busy waiting*/}
            System.out.println("PING");
            shared.setWhoCanPrint(2);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*COMPLETE */

    }
}

class Pong extends Thread {

    private SharedObject shared;

    public Pong(SharedObject sharedObject) {
        this.shared = sharedObject;
    }
    /* COMPLETE*/


    public void run() {
        while (true) {
            /*COMPLETE */
            while (shared.getWhoCanPrint() != 2) {/*Busy waiting*/}
            System.out.println("    PONG");
            shared.setWhoCanPrint(1);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}

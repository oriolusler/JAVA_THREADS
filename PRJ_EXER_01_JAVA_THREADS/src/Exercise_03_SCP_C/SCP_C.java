package Exercise_03_SCP_C;


public class SCP_C {

    public static void main(String[] args) {
        Storage storage = new Storage();

        Counter counter = new Counter(storage);
        Printer printer = new Printer(storage);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }

        counter.stop();
        printer.stop();
    }
}

class Storage {
    private volatile int value = -1000;

    private volatile boolean canBeSet = true;
    private volatile boolean canBeGot = false;

    public int getValue() {

        int safeCopy;

        while (!canBeGot) { /*busy waiting*/ }

        try {
            Thread.sleep(0, 10);
        } catch (InterruptedException ie) {
        }

        safeCopy = value;

        canBeGot = false;
        canBeSet = true;

        /*safe copy avoid race condition*/
        return safeCopy;
    }

    public void setValue(int value) {

        while (!canBeSet) { /*busy waiting*/}

        // once a value has been set it can be got but a new one can't be set
        // until the old one has been got
        this.value = value;
        canBeSet = false;
        canBeGot = true;
    }
}


class Counter extends Thread {
    private Storage storage;

    public Counter(Storage storage) {
        this.storage = storage;
        // once created, instances of Counter start themselves
        this.start();
    }

    public void run() {
        int i;
        while (true) {
            for (i = 0; i <= 9; i++) {
                storage.setValue(i);
            }
        }
    }
}

class Printer extends Thread {
    private Storage storage;

    public Printer(Storage storage) {
        this.storage = storage;
        // once created, instances of Printer start themselves
        this.start();
    }

    public void run() {
        int i;
        while (true) {
            i = storage.getValue();
            for (int n = 1; n <= i; n++) {
                System.out.print(" ");
            }
            System.out.println(i);
        }
    }
}

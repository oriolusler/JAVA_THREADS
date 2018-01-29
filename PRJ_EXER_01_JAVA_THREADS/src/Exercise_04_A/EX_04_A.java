package Exercise_04_A;

public class EX_04_A {

    public static void main(String[] args) throws InterruptedException {
        LineThread a, b;

        a = new LineThread("A");
        b = new LineThread("  B");

        /* COMPLETE */
        a.start();
        b.start();

        Thread.sleep(5000);

        a.stop();
        b.stop();

        System.out.println("\n\nA wrote: " + a.getCounter() + " times");
        System.out.println("B wrote: " + b.getCounter() + " times");

    }

}

class LineThread extends Thread {

    private String line; // the line to write
    private int counter; // counter

    public LineThread(String line) {
        this.line = line;
        counter = 0;
    }

    public int getCounter() {
        return this.counter;
    }

    /* COMPLETE */

    @Override
    public void run() {
        while (true) {
            System.out.println(line + " " + counter);
            counter++;
        }

    }

}

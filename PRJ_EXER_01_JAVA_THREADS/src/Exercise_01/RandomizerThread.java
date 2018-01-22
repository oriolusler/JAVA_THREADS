package Exercise_01;

import java.util.Random;

public class RandomizerThread extends Thread {

    Random random = new Random();

    private int theNumber = -1000;
    /* COMPLETE */

    public int getRandom() {
        return theNumber;
    }

    public RandomizerThread() {
    }

    @Override
    public void run() {
        theNumber = random.nextInt(10);
        System.out.println("Number random generated is " + theNumber);
    }
}
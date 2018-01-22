package Exercise_01;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RandomizerThread[] threadTable = new RandomizerThread[5];

        /* COMPLETE */

        int sum = 0;

        for (int i = 0; i < threadTable.length; i++) {
            threadTable[i] = new RandomizerThread();
        }

        for (int i = 0; i < threadTable.length; i++) {
            threadTable[i].start();
        }

        for (int i = 0; i < threadTable.length; i++) {
            threadTable[i].join();
            sum += threadTable[i].getRandom();
        }

        System.out.println("number final:" + sum);


    }
}

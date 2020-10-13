package ru.geekbrains;

public class Arrays {
    private static final int size = 10_000_000;
    private static float[] arr0 = new float[size];
    private static float[] arr1 = new float[size];
   

    private static void initArray(float[] arr) {
        Arrays.fill(arr, 1);
    }

    private static void fill(float[] arr, int i) {
    }

    private static void processArray(float[] arr, int ndx) {
        for (int i = 0; i < arr.length; i++) {
            int j = i + ndx;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + j / 5f) * Math.cos(0.2f + j / 5f) * Math.cos(0.4f + j / 2f));
        }
    }

    public static void multiThreadWork(int initIndex, int partSize) {
        float[] partArr = new float[partSize];
        System.arraycopy(arr1, initIndex, partArr, 0, partSize);
        processArray(partArr, initIndex);
        System.arraycopy(partArr, 0, arr1, initIndex, partSize);
    }

    public static void main(String[] args) {
        System.out.println("Single thread processing");
        initArray(arr0);
        long start = System.currentTimeMillis();
        processArray(arr0,0);
        long stop = System.currentTimeMillis();
        System.out.printf("Worktime: %d miliseconds\n",stop - start);

        int numThreads = 8;
        int partSize = size / numThreads;
        System.out.printf("Multi (%d) thread processing\n",numThreads);
        initArray(arr1);

       Work[] w = new Work[numThreads];
        start = System.currentTimeMillis();
        for (int i = 0; i < numThreads; i++) {
            int thisPartSize = i == numThreads - 1 ? size - i * partSize : partSize;
            w[i] = new Work("Thread " + i, i * partSize,thisPartSize);
            w[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            try {
                w[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop = System.currentTimeMillis();
        System.out.printf("Worktime: %d miliseconds\n",stop - start);


    }

}

package ru.geekbrains;

public class Work extends Thread{
    private int begin;
    private int size;

    public Work(String s, int i, int thisPartSize) {
    }

    void Work(String name, int begin, int size) {
        getaSuper ( name );
        this.begin = begin;
        this.size = size;
    }

    private static void getaSuper(String name) {
    }

    @Override
    public void run() {
        ArraysWorks.multiThreadWork(begin,size);
    }
}

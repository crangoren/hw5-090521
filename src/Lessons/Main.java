package Lessons;

import java.util.Arrays;

public class Main extends Thread{
    static final int size = 10000000;
    static final int h = size / 2;


    public static void main(String[] args) throws InterruptedException {

        calculate1();
        calculate2();

    }

    public static void calculate1() {
        float[] arr1 = new float[size];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = 1.0f;
        }

        long a = System.currentTimeMillis();
        for (int j = 0; j < arr1.length; j++) {
            arr1[j] = (float)(arr1[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
        }

        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void calculate2() throws InterruptedException {
        float[] arr1 = new float[size];
        float[] a1 = new float[size/2];
        float[] a2 = new float[size/2];

        for (int k = 0; k < arr1.length; k++) {
            arr1[k] = 1.0f;
        }
        System.currentTimeMillis();
        long b = System.currentTimeMillis();


        Thread thread1 = new Thread(() -> {
            System.arraycopy(arr1, 0, a1, 0, h);
            for (int j = 0; j < a1.length; j++) {
                a1[j] = (float)(a1[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
            }
            System.arraycopy(a1, 0, arr1, 0, h);

        });

        Thread thread2 = new Thread(() -> {

            System.arraycopy(arr1, h, a2, 0, h);
            for (int j = 0; j < a2.length; j++) {
                a2[j] = (float)(a2[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
            }
            System.arraycopy(a2, 0, arr1, h, h);
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();



        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - b);

    }


}
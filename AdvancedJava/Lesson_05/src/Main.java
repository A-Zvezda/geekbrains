import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    //static final int size = 10000000;
    static final int size = 10;
    static final int h = size / 2;
    static float[] arr = new float[size];

    public static void main(String[] args) {
        arr = fillArray(arr.clone());
        System.out.println();
        float[][] ready = chunkArray(arr,3);
        System.out.println(ready);

    }

    public static float[][] chunkArray(float[] array, int chunkSize) {
        int numOfChunks = (int)Math.ceil((double)array.length / chunkSize);
        float[][] output = new float[numOfChunks][];

        for(int i = 0; i < numOfChunks; ++i) {
            int start = i * chunkSize;
            int length = Math.min(array.length - start, chunkSize);

            float[] temp = new float[length];
            System.arraycopy(array, start, temp, 0, length);
            output[i] = temp;
        }
        return output;
    }

    public static float[] fillArray (float[] array) {
        for(int i = 0; i < array.length; ++i) {
            array[i] = 1;
        }
        return array;
    }
}

class myThreads {

    public static void runThreads () {


    }

}

class MyThread extends Thread {
    float[] arr;
    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }
    }
    public MyThread (float[] arr) {
        System.arraycopy(arr, 0, this.arr, 0, arr.length);
    }
}

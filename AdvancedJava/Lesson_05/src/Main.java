import java.util.Arrays;


/**
 * 1. Необходимо написать два метода, которые делают следующее:
 *
 * 1) Создают одномерный длинный массив, например:
 * static final int size = 10000000;
 * static final int h = size / 2;
 * float[] arr = new float[size];
 * 2) Заполняют этот массив единицами;
 * 3) Засекают время выполнения: long a = System.currentTimeMillis();
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis();
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
 * Отличие первого метода от второго:
 * Первый просто бежит по массиву и вычисляет значения.
 * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
 *
 * Пример деления одного массива на два:
 * System.arraycopy(arr, 0, a1, 0, h);
 * System.arraycopy(arr, h, a2, 0, h);
 *
 * Пример обратной склейки:
 * System.arraycopy(a1, 0, arr, 0, h);
 * System.arraycopy(a2, 0, arr, h, h);
 *
 * Примечание:
 * System.arraycopy() копирует данные из одного массива в другой:
 * System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
 * По замерам времени:
 * Для первого метода надо считать время только на цикл расчета:
 * for (int i = 0; i < size; i++) {
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * }
 * Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 * @author Aleksandr Zvezda
 */

public class Main {
    static final int size = 10000000;
    static final int h = size / 4;
    static float[] arr = new float[size];

    public static void main(String[] args) {

        MyThreadMultipleArray t1 = new MyThreadMultipleArray(arr,0);

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyThreadMultipleArray t2 = new MyThreadMultipleArray(arr,h);
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.equals(t1.getArr(), t2.getArr()));

    }

}


/**
 * Класс рачёта одномерного массива
 */
class MyThread extends Thread {
    private float[] arr;
    private int shift = 0;
    @Override
    public void run() {
        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = (float) (this.arr[i] * Math.sin(0.2f + (i+this.shift) / 5) * Math.cos(0.2f + (i+this.shift) / 5) * Math.cos(0.4f + (i+this.shift) / 2));
        }
    }

    /**
     * Конструктор класса для расчета не разбитого массива
     * @param arr - массив для выолнения действий
     */
    public MyThread (float[] arr) {
        this.arr  = arr.clone();

    }

    /**
     * Конструктор класса для расчёта разбитого массива
     * @param arr - массив для выполнения действий
     * @param shift - сдвиг, когда массив разбить необходмо указать сдвиг для отчёта
     */
    public MyThread (float[] arr, int shift) {
        this.arr  = arr;
        this.shift = shift;
    }

    /**
     * возвращает массив
     * @return - массив
     */
    public float[] getArr() {
        return arr.clone();
    }
}

/**
 * Класс для выполнения операций с массивом
 */

class MyThreadMultipleArray extends Thread {
    private float[] arr;
    private int chunkSize = 0;

    @Override
    public void run() {
        long a = System.currentTimeMillis();
        if (this.chunkSize == 0) {
            MyThread t1 = new MyThread(this.arr);
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.arr = t1.getArr();
        } else {
            float[][] chunkedArray = chunkArray(this.arr,this.chunkSize).clone();
            MyThread [] threads = new MyThread[chunkedArray.length];
            int shift = 0;
            for (int i = 0; i < threads.length; i++) {
                    threads[i] = new MyThread(chunkedArray[i].clone(),shift);
                    threads[i].start();
                    shift += chunkedArray[i].length;
                }
             for (int i = 0; i< threads.length; i++ ) {
                    try {
                    threads[i].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            for (int i = 0; i< threads.length; i++ ) {
                chunkedArray[i] = threads[i].getArr();
            }
             this.arr = combine(chunkedArray);
            }

            System.out.println(System.currentTimeMillis() - a);
    }

    /**
     * Констурктор
     * @param arr - массив для операции
     * @param chunkSize - размер
     */
    public MyThreadMultipleArray (float[] arr, int chunkSize) {
        this.arr  = arr.clone();
        this.chunkSize = chunkSize;
    }

    /**
     * Метод разбиеене массива
     * @param array - массив для разбиения
     * @param chunkSize - колчестов элементов в разбитых массивах
     * @return - разбитый двумерный массив
     */
    private float[][] chunkArray(float[] array, int chunkSize) {
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

    /**
     * Склейка двумерного массива обратно в одномерный
     * @param array - массив для склейки
     * @return - склееный одномерный массив
     */
    private float[] combine(float[][] array){
        float[] result = new float[this.arr.length];
        int k = 0;
        for (int i = 0; i < array.length; i ++) {
            for (int j = 0; j < array[i].length; j ++) {
                result[k] = array[i][j];
                k++;
            }
        }
        return result;
    }

    public float[] getArr() {
        return arr.clone();
    }
}

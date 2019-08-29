package inputOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class inputOutput {
    public static void main (String[] args) {
        task01();
        task02();
        task03();
    }

    public static void task01 () {
        try (InputStream in = new BufferedInputStream(new FileInputStream("res/50b.txt"))) {
            int x;
            while ((x = in.read()) != -1) {
                System.out.print(x);
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

    public static void  task02 () {
        int capacity = 5;
        List<InputStream> list = new ArrayList<InputStream>(capacity);

        try {
            list.add(new FileInputStream("res/1.txt"));
            list.add(new FileInputStream("res/2.txt"));
            list.add(new FileInputStream("res/3.txt"));
            list.add(new FileInputStream("res/4.txt"));
            list.add(new FileInputStream("res/5.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Enumeration<InputStream> e = Collections.enumeration(list);
        SequenceInputStream sequenceInputStream = new SequenceInputStream(e);
        try {
            int x;
            while ((x = sequenceInputStream.read()) != -1) {
            System.out.write(x);
            }
        } catch (Exception c) {
            c.printStackTrace();
        }
        try {
            sequenceInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void task03 () {
        long start = System.currentTimeMillis();
        final int BUFFER_SIZE = 1800;
        byte[] buffer = new byte[BUFFER_SIZE];
        try (InputStream inputStream = new FileInputStream("res/big.txt")) {

            int read = 0;
            long readTime = System.currentTimeMillis();
            while((read=inputStream.read(buffer)) != -1){
                if (read == BUFFER_SIZE) {
                    System.out.println(new String(buffer));
                } else {
                    byte[] trimBuffer = new byte[read];
                    System.arraycopy(buffer, 0, trimBuffer, 0, read);
                    System.out.println(new String(trimBuffer));
                }

            }
            System.out.println("Общее время чтения " + (System.currentTimeMillis() - readTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Общее время выполнения операции с файлом " + (System.currentTimeMillis() - start));
       // System.out.println("Обработано " + i);
    }

}


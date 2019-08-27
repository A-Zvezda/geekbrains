package inputOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class inputOutput {
    public static void main (String[] args) {
        //task01();
        //task02();
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
        List<InputStream> list = new ArrayList<InputStream>(5);

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

    }

    public static void task03 () {
        StringBuilder builder = new StringBuilder();
        final int BUFFER_SIZE = 500;
        byte[] buffer = new byte[BUFFER_SIZE];
        try  {
            InputStream inputStream = new FileInputStream("res/1.txt");
            int read = 0;
            while((read=inputStream.read(buffer)) != -1){
               // for (int i = 0 ; i < buffer.length; i++) {
                  //  char c = (char) buffer[i];
                 //   System.out.print(buffer.toString());
               // }
                System.out.println(new String(buffer));
                //char c = (char) buffer[1];
//                builder.append(c);
//                System.out.println(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (PipedInputStream in = new PipedInputStream();
//             PipedOutputStream out = new PipedOutputStream(in)) {
//            for (int i = 0; i < 10; i++) {
//                out.write(i);
//            }
//
//            int x;
//            while ((x = in.read()) != -1) {
//                System.out.print(x + " ");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}


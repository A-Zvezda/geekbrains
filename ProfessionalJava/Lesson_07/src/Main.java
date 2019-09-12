import testInterfaces.AfterSuite;
import testInterfaces.BeforeSuite;
import testInterfaces.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Main {


    public static void test(Class inClass)  {
        Method[] m = inClass.getDeclaredMethods();
        Map<Integer,Method> testMap = new TreeMap<>();
        int beforeCount = 0;
        int afterCount = 0;
        int keyValue = 0;
        for (Method o: m ) {
            //System.out.println(o.getReturnType() + " ||| " + o.getName() + " ||| " + Arrays.toString(o.getParameterTypes()));

            BeforeSuite beforeAnnotation = o.getAnnotation(BeforeSuite.class);
            AfterSuite afterAnnotation = o.getAnnotation(AfterSuite.class);
            if (beforeAnnotation != null) {
                beforeCount ++;
                testMap.put(-1,o);
            }
            if (afterAnnotation!= null) {
                afterCount ++;
                testMap.put(2147483647,o);
            }
            Test testAnnotation = o.getAnnotation(Test.class);
            if (testAnnotation != null) {
                keyValue = testAnnotation.value();
                if (testMap.get(keyValue) != null) {
                    while (testMap.get(keyValue) != null) {
                        keyValue = testAnnotation.value() + 1;
                    }
                } else {
                    keyValue = testAnnotation.value();
                }

                testMap.put(keyValue, o);
            }
            if (afterCount > 1 || beforeCount >1 ) {
                throw new  RuntimeException();
            }
        }

        try {
            Class<?> clazz =  Class.forName(inClass.getName());
            Object newTest = clazz.newInstance();
            for (Map.Entry<Integer,Method> e : testMap.entrySet()) {
                try {
                    e.getValue().invoke(newTest);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

     }


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        test(TestedClass.class);

    }
}

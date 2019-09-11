import java.lang.annotation.*;
import java.lang.reflect.Method;


public class Main {
    @Test(value = 10)
    public void advAnnotatedMethod() {
        System.out.println("...");
    }

    public static void main(String[] args) {
        try {
            Method m = Main.class.getMethod("advAnnotatedMethod", null);
            Test annotation = m.getAnnotation(Test.class);
            System.out.println("value: " + annotation.value());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

import testInterfaces.*;


public class TestedClass {

    @BeforeSuite
    public void before() {
        System.out.println("before");
    }
    @Test
    public void one() {
        System.out.println("one");
    }
    @Test(value =  1)
    public void two() {
        System.out.println("two");
    }

    public void three() {
        System.out.println("three");
    }
    @AfterSuite
    public void after() {
        System.out.println("after");
    }
}

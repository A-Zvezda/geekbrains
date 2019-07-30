public class Main {

    public static void main(String[] args) {
        Employee[] employeesArray = new Employee[5];
        employeesArray[0] = new Employee("Ivanov Ivan", "Jr.Engineer", " ivivan@mailbox.com ", "892312312", 30000, 30);
        employeesArray[1] = new Employee("Petrov Ivan", "Jr.Engineer", " petov@mailbox.com ", "892312313", 30000, 18);
        employeesArray[2] = new Employee("Sidorov Ivan", "Jr.Engineer", " sidorov@mailbox.com ", "892312314", 30000, 35);
        employeesArray[3] = new Employee("Mihalov Ivan", "Mi.Engineer", " mihalov@mailbox.com ", "892312315", 33000, 41);
        employeesArray[4] = new Employee("Golustayan Ivan", "Sen.Engineer", "golustayn@mailbox.com ", "892312316", 40000, 50);

        for (int i = 0; i < employeesArray.length; i ++) {
            if (employeesArray[i].getAge() > 40) {
                employeesArray[i].printAllData();
            }
        }
        Dog dog1 = new Dog();
        dog1.setSwim(100);
        dog1.printOutInfo();
        System.out.println(dog1.swim(20));
        Cat cat1 = new Cat();

        cat1.printOutInfo();
        cat1.swim(12);
    }
}

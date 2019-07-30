public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    public Employee (String fullName, String position, String email, String phoneNumber, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public String getFullName () {
        return  this.fullName;
    }

    public String getPosition () {
        return  this.position;
    }

    public String getEmail () {
        return this.email;
    }
    public String getPhoneNumber () {
        return this.phoneNumber;
    }

    public int getSalary () {
        return this.salary;
    }

    public int getAge () {
        return this.age;
    }

    public void setFullName (String fullName) {
        this.fullName = fullName;
    }

    public void setPosition (String position) {
        this.position = position;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSalary (int salary) {
        this.salary = salary;
    }

    public void setAge (int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.err.println("Введён не корретный возраст");
        }

    }

    public void printAllData () {
        System.out.println("ФИО: " + this.fullName + "; Должность: " + this.position + " ;email: " + this.email + "; телефон; " + this.phoneNumber + "; зарплата: " + this.salary + "; возраст: " + this.age);
    }

}

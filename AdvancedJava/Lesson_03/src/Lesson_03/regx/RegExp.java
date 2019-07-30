/**
 * Создать метод для проверки пароля
 *
 * 1 Обязательно есть хоть 1 цифра
 * 2 Не менее 8 символов и не более 20
 * 3 Должны быть большие и маленькие буквы
 * 4 Обязательно дожен быть спец символ
 * @author      Aleksandr Zvezda
 */

package Lesson_03.regx;

public class RegExp {
    public static void main(String[] args) {
        System.out.println(checkPassword("aaaaaaAAA1@"));
        System.out.println(checkPassword("aaaaaAAA1@@@#@@@@#*&(&*(&(&*))&)"));
    }

    /**
     * проверка строки на соответсвованию требованиям
     * @param password - строка для проверки
     * @return - boolean результат проверки
     */
    public static boolean checkPassword(String password) {

        String onlyThisSymbols = "^[0-9a-zA-Z!@#$%^&*()+=_]*$";
        String oneDigAtLeast = "(?=.*[0-9])";
        String lowerCaseAtLeastEn= "(?=.*[a-z])";
        String upperCaseAtLeastEn = "(?=.*[A-Z])";
        String noSpace = "(?=\\S+$)";
        String limit = ".{8,20}";
        String specialChar = "(?=.*[!@#$%^&*()+=_])";
        String pattern = oneDigAtLeast + lowerCaseAtLeastEn  + upperCaseAtLeastEn  + specialChar + noSpace + limit;
        return password.matches(pattern) & password.matches(onlyThisSymbols);

    }
}

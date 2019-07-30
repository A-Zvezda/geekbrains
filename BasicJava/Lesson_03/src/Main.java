/**
 * 1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать;
 * 2. Реализовать логику более умного компьютера, который определяет свой ход на основании соседних клеток;
 * 3. Найти в коде неоптимальные места и улучшить их;
 * 4. *Усилить логику алгоритмом с подсчётом очков для каждой клетки.
 * @author Aleksandr Zvezda
 */

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String USER_SIGN_FIRST = "X";
    private static final String USER_SIGN_SECOND = "O";
    private static final String NOT_SIGN = "*";
    private static int aiLevel = 0;
    private static final int ARRAY_DIMENSION = 3;
    private static String[][] field = new String[ARRAY_DIMENSION][ARRAY_DIMENSION];

    public static void main(String[] args) {
        mainMenu();
    }
    /**
     * Метод вывода вида игры
     */
    private static void mainMenu () {
        System.out.println("Выберите режим игры: ");
        System.out.println("1. Игра против компьютера.");
        System.out.println("2. 2 игрока.");
        System.out.println("3. Выход.");
        int i = 0;
        Scanner sc = new Scanner(System.in);
        i = sc.nextInt();
        switch (i) {
            case 1: {
                pcLevelChoose();
                break;
            }
            case 2: {
                modeTwoPlayers();
                break;
            }
            case 3: {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Было введено неверное значение!");
            }
        }

    }
    /**
     * Метод для игры двух человек
     */
    private static void modeTwoPlayers() {
        initField();
        while (true) {
            printField();
            userTurn(USER_SIGN_FIRST, 1);

            if (checkTurn(USER_SIGN_FIRST)) {
                System.out.println("Первый игрок победил!");
                printField();
                break;
            }
            if (!checkTurnsEnd()) {
                System.out.println("Ходы окончены!");
                printField();
                break;
            }
            printField();
            userTurn(USER_SIGN_SECOND, 2);
            if (checkTurn(USER_SIGN_SECOND)) {
                System.out.println("Второй игрок победил!");
                printField();
                break;
            }

        }
    }
    /**
     * Метод игры против компьютера
     */
    private static void modeAgainstAI() {
        int count = 0;
        initField();
        while (true) {
            printField();
            userTurn(USER_SIGN_FIRST, 0);
            if (checkTurn(USER_SIGN_FIRST)) {
                System.out.println("Вы выйгали!");
                printField();
                break;
            }
            if (!checkTurnsEnd()) {
                System.out.println("Ходы окончены!");
                printField();
                break;
            }
            aiTurn();
            if (checkTurn(USER_SIGN_SECOND)) {
                System.out.println("Коипьютер выйграл!");
                printField();
                break;
            }

        }
    }
    /**
     * Метод выбора сложности компьютера
     */
    private static void pcLevelChoose() {
        System.out.println("Выберите сложность компьютера: ");
        System.out.println("1. Простой.");
        System.out.println("2. Продвинутый не даёт выйграть.");
        System.out.println("3. Продвинутый c поиском ходов по диагонали.");
        System.out.println("4. Главное меню.");
        String userInputValue;
        Scanner userInput = new Scanner(System.in);
        userInputValue = userInput.nextLine();
        switch (userInputValue) {
            case "1": {
                aiLevel = 0;
                modeAgainstAI();
                break;
            }
            case "2": {
                aiLevel = 1;
                modeAgainstAI();
                break;
            }
            case "3": {
                aiLevel = 2;
                modeAgainstAI();
                break;
            }
            case "4": {
                mainMenu();
                break;
            }
            default: {
                System.out.println("Было введено неверное значение!");
            }
        }
    }
    /**
     * Метод иницализирующий игровое поле
     */
    private static void initField() {
        for (int i = 0; i < ARRAY_DIMENSION; i++) {
            for (int j = 0; j < ARRAY_DIMENSION; j++) {
                field[i][j] = NOT_SIGN;
            }
        }
    }
    /**
     * Метод выводящий поле на экран
     */
    private static void printField() {
        for (int i = 0; i <= ARRAY_DIMENSION; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < ARRAY_DIMENSION; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < ARRAY_DIMENSION; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
    /**
     * Метод хода человека
     * @param sign - символ игрока делающий ход
     * @param i - номер игрока делающий ход
     */
    private static void userTurn(String sign, int i) {
        int x = -1;
        int y = -1;
        do {
            if (i == 0) {
                System.out.println("Введите координаты x y (1 - " + ARRAY_DIMENSION + "): ");
            } else {
                System.out.println("Игрок " + i + ". Введите координаты x y (1 - " + ARRAY_DIMENSION + "): ");
            }
            x = parseUserInputInNumber();
            y = parseUserInputInNumber();
        }
        while (checkIsCellBusy(x, y));
        field[x][y] = sign;
    }
    /**
     * Метод хода компьютера
     */
    private static void aiTurn() {
        int x = -1;
        int y = -1;
        boolean aiWin = false;
        boolean userWin = false;
        if (aiLevel == 0) {
            do {
                Random rnd = new Random();
                x = rnd.nextInt(ARRAY_DIMENSION);
                y = rnd.nextInt(ARRAY_DIMENSION);
            }
            while (checkIsCellBusy(x, y));
        } else if (aiLevel == 1) {
            //Ищем выйгрыщные ходы, если не находим выводим случайным образм координаты
            for (int i = 0; i < ARRAY_DIMENSION; i++) {
                for (int j = 0; j < ARRAY_DIMENSION; j++) {
                    if (!checkIsCellBusy(i, j)) {
                        field[i][j] = USER_SIGN_SECOND;
                        if (checkTurn(USER_SIGN_SECOND)) {
                            x = i;
                            y = j;
                            aiWin = true;
                        }
                        field[i][j] = NOT_SIGN;
                        if (aiWin) {
                            break;
                        }
                        field[i][j] = NOT_SIGN;
                    }
                }
            }
            if (!aiWin) {
                for (int i = 0; i < ARRAY_DIMENSION; i++) {
                    for (int j = 0; j < ARRAY_DIMENSION; j++) {
                        if (!checkIsCellBusy(i, j)) {
                            field[i][j] = USER_SIGN_FIRST;
                            if (checkTurn(USER_SIGN_FIRST)) {
                                x = i;
                                y = j;
                                userWin = true;
                            }
                            field[i][j] = NOT_SIGN;
                            if (userWin) {
                                break;
                            }
                        }
                    }
                }
            }
            if (!aiWin && !userWin) {
                do {
                    Random rnd = new Random();
                    x = rnd.nextInt(ARRAY_DIMENSION);
                    y = rnd.nextInt(ARRAY_DIMENSION);
                }
                while (checkIsCellBusy(x, y));
            }
        } else if (aiLevel == 2) {
            boolean turnNotFound = true;
            int counter = 0;
            do {

                do {
                    Random rnd = new Random();
                    x = rnd.nextInt(ARRAY_DIMENSION);
                    y = rnd.nextInt(ARRAY_DIMENSION);
                } while (checkIsCellBusy(x, y));
                int xForFoundDown = 0;
                int xForFoundUp = 0;
                int yForFoundDown = 0;
                int yForFoundUp = 0;
                // Тупое ограничение по поиску соседних клеток
                if (x > 0 && x < 3) {
                    xForFoundDown = x - 1;
                    xForFoundUp = x - 1;
                } else if (x == 0) {
                    xForFoundDown = x;
                    xForFoundUp = x + 1;
                } else if (x == 3){
                    xForFoundDown = x - 1;
                    xForFoundUp = x ;
                }
                if (y > 0 && y < 3) {
                    yForFoundDown = y - 1;
                    yForFoundUp = y - 1;
                } else if (y == 0) {
                    yForFoundDown = y;
                    yForFoundUp = y + 1;
                } else if (y == 3){
                    yForFoundDown = y - 1;
                    yForFoundUp = y ;
                }
                for (int i = xForFoundDown; i < xForFoundUp; i ++ ) {
                    for (int j = yForFoundDown; j < yForFoundUp; j++) {
                        if (i != x && j != y ) {
                            if (field[i][j].equals(USER_SIGN_SECOND)) {
								turnNotFound = false;
								break;
                            }
							if (!turnNotFound) {
								break;
							}
                        }
                    }
                }
                counter ++;
                if (counter > 9) {
                    turnNotFound = false;
                }
            } while (turnNotFound);
        }
		// Вес хода
		// Прверяем найденное решение
        // Если выйгрышно оно или нет
        field[x][y] = USER_SIGN_SECOND;
        if (checkTurn(USER_SIGN_SECOND)) {
            aiWin = true;
        }
        field[x][y] = NOT_SIGN;
        // Определяем, может ли победить ИИ данным ходом
        // Поиск более оптимального хода путём перебора
        if (!aiWin) {
            for (int i = 0; i < ARRAY_DIMENSION; i++) {
                for (int j = 0; j < ARRAY_DIMENSION; j++) {
                    if (!checkIsCellBusy(i, j)) {
                        field[i][j] = USER_SIGN_SECOND;
                        if (checkTurn(USER_SIGN_SECOND)) {
                            x = i;
                            y = j;
                            aiWin = true;
                        }
                        field[i][j] = NOT_SIGN;
                        if (aiWin) {
                            break;
                        }
                    }
                }
            }
        }
		// Определяем может ли победить игрок текущим ходом перебором
		// Если возможно - не даём сделать ход
        if (!aiWin) {
             for (int i = 0; i < ARRAY_DIMENSION; i++) {
                 for (int j = 0; j < ARRAY_DIMENSION; j++) {
                        if (!checkIsCellBusy(i, j)) {
                            field[i][j] = USER_SIGN_FIRST;
                            if (checkTurn(USER_SIGN_FIRST)) {
                                x = i;
                                y = j;
                                userWin = true;
                            }
                            field[i][j] = NOT_SIGN;
                            if (userWin) {
                                break;
                            }
                        }
                 }
             }
        }
        //В остальных случаях ставим найденое значние по соседений клетке

        field[x][y] = USER_SIGN_SECOND;
        System.out.println("Ход компьютера:");

    }

    /**
     * Метод валидации заправшивает ячейки на корректность
     * @param x - координаты по горизонтали
     * @param y - координаты по вертикали
     * @return boolian - признак валидации
     */
    private static boolean checkIsCellBusy(int x, int y) {
        if (x < 0 || y < 0 || x > ARRAY_DIMENSION - 1 || y > ARRAY_DIMENSION - 1) {
            System.out.println("Ячейка занята. Введите координаты другой ячейки");
            return false;
        }
        return !field[x][y].equals(NOT_SIGN) ;
    }
    /**
     * Метод поиска свободных ячеек для хода
     * @return boolian - признак присутсвия такой ячейки
     */
    private static boolean checkTurnsEnd() {
        boolean  emptyCellFound = false;
        int i = 0;
        while (i < ARRAY_DIMENSION && !emptyCellFound) {
            int j = 0;
            while (!emptyCellFound && j < ARRAY_DIMENSION) {
                emptyCellFound = field[i][j].equals(NOT_SIGN);
                j ++;
            }
            i++;
        }

        return emptyCellFound;
    }
    /**
     * Метод проверки на вйыгрышний ход
     * @param sign - символ игрока делающий ход
     * @return boolian - признак победы
     */
    private static boolean checkTurn(String sign) {
        for (int i = 0; i < ARRAY_DIMENSION; i++) {
            if (field[i][0].equals(sign) && field[i][1].equals(sign) && field[i][2].equals(sign)) {
                return true;
            }
        }
        for (int j = 0; j < ARRAY_DIMENSION; j++) {
            if (field[0][j].equals(sign) && field[1][j].equals(sign) && field[2][j].equals(sign)) {
                return true;
            }
        }
        if (field[0][0].equals(sign) && field[1][1].equals(sign) && field[2][2].equals(sign)) {
            return true;
        }
        if (field[0][2].equals(sign) && field[1][1].equals(sign) && field[2][0].equals(sign)) {
            return true;
        }

        return false;
    }
    /**
     * Метод для ввода данных о выбранной ячейке и валидности ввода
     * @return boolian - признак валидации
     */
    private static int parseUserInputInNumber () {

        int userInputIntValue = 0;
        String userInputValue;

        boolean correctEnter = true;
        do {
            boolean errorInput = false;
            Scanner userInput = new Scanner(System.in);
            userInputValue = userInput.nextLine();
            try {
                userInputIntValue = Integer.parseInt(userInputValue) - 1;
                correctEnter = userInputIntValue >= 0 && userInputIntValue <= 3;
            } catch (NumberFormatException catchedException) {
                System.out.println("Введенное значение не является числом! Повторите ввод! Введите значение от 0 до 3");
                //catchedException.printStackTrace();
                errorInput = true;
            }
            if (!correctEnter && !errorInput) {
                System.out.println("Введенное значение находится за приделами поля, введите значение от 0 до 3");
            }
        } while (!correctEnter);
        return userInputIntValue;
    }

}
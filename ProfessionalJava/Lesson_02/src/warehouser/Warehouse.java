package warehouser;

import org.sqlite.core.DB;

import java.util.ArrayList;
import java.util.Scanner;

public class Warehouse {

    public static void main(String[] args) {
		boolean work = true;
		Scanner userInput = new Scanner(System.in);
    	System.out.println("Начало работы с БД...");
		DBProcessor.connection();
		System.out.println("Создание таблицы...");
		DBProcessor.createTable();
		System.out.println("Отчистка таблицы...");
		DBProcessor.emptyTable();
		System.out.println("Заполнение таблицы...");
		DBProcessor.fillTable();
		System.out.println("Добрый день, Пользовталь. У вас есть три комнады: /цена /сменитьцену /товарыпоцене /выход ");
		do {
			String userInputData = userInput.nextLine();
			String[] tokens = userInputData.split(" ");
			if (tokens != null) {
				switch (tokens[0].trim()) {
					case "/цена": {
						findItem(tokens);
						break;
					}
					case "/товарыпоцене": {
						findItemsByPrice(tokens);
						break;
					}
					case "/сменитьцену": {
						changePrice(tokens);
						break;
					}
					case "/выход": {
						work = false;
						break;
					}
					default:
						System.out.println("Команда не найдена");
						break;
				}
			}

			if (work) {
				System.out.println("Введите команду.");
			}
		} while (work);
		DBProcessor.closeConnection();
		userInput.close();
	}

	public static void changePrice(String[] tokens) {
		int i = 0;
    	if (tokens.length <=2 ) {
			System.out.println("Ввдёное меньше параметров чем надо ");
			return;
		}
    	try {
			i = Integer.parseInt(tokens[2]);
		} catch (NumberFormatException caughtException) {
			System.out.println("Ввдёное значние не является числом ");
			caughtException.printStackTrace();
			return;
		}

    	if (DBProcessor.changePrice(tokens[1], i)) {
			System.out.println("Цена товара " + tokens[1] + " изменена ");
		} else {
			System.out.println("Что-то пошло не так");
		}
	}
	public static void findItem(String[] tokens) {
		if (tokens.length <=1 ) {
			System.out.println("Ввдёное меньше параметров чем надо ");
			return;
		}
		String res = DBProcessor.findItem(tokens[1]);
		if (res != null) {
			System.out.println("Цена товара " + tokens[1] + ": равна " + res);
		} else {
			System.out.println("Товар " + tokens[1] + " не найден!");
		}
	}
	public static void findItemsByPrice(String[] tokens) {
		int minPrice = 0;
		int maxPrice = 0;
    	if (tokens.length <=2 ) {
			System.out.println("Ввдёное меньше параметров чем надо ");
			return;
		}
		try {
			minPrice = Integer.parseInt(tokens[1]);
			maxPrice = Integer.parseInt(tokens[2]);
		} catch (NumberFormatException caughtException) {
			System.out.println("Ввдёное значние не является числом ");
			caughtException.printStackTrace();
			return;
		}
		ArrayList<Item> items = DBProcessor.findItemByPrice(minPrice,maxPrice);
		if (items.size() == 0) {
			System.out.println("Товры заданом интервале цен не найдены ");
		} else {
			for (Item o : items) {
				System.out.println(o.toString());
			}
		}
	}

}

/**
 * Класс Миска
 * volumeCurrent - объём еды в миске.
 * volumeMax - максимальный объём миски.
 * @author Aleksandr Zvezda
 */

public class Bowl {
	int volumeCurrent;
	int volumeMax;

	/**
	 * Конструктор класса. Задаёт первоначальные параметры - Объём миски и её заполненность кормом.
	 */
	public Bowl () {
		this.volumeMax = 60;
		this.volumeCurrent = 10;
	}

	/**
	 * Метод возвращает максимальный объём миски.
	 * @return int - максимальный объём миски.
	 */
	public int getVolumeMax () {
		return volumeMax;
	}

	/**
	 * Метод возвращает текущий объём корма.
	 * @return int - текущий объём корма.
	 */
	public int getVolumeCurrent () {
		return volumeCurrent;
	}

	/**
	 * Метод изменения объёма корма в миске с проверкой на превышение максимального объёма.
	 * @param volumeCurrent - Величина на которую изменяем объём. Если < 0 тогда уменьшаем, > 0 увеличиваем.
	 * @return boolean - true всё прошло успешно, false - что-то пошло не так.
	 */
	public boolean changeVolumeCurrent (int volumeCurrent) {
		boolean changeSuccessful = false;
		if (volumeCurrent < 0 ) {
			if ((this.volumeMax + volumeCurrent) > 0){
				if (volumeCurrent + this.volumeCurrent > 0 ) {
					this.volumeCurrent += volumeCurrent;
					changeSuccessful = true;
				} else {
				System.out.println("В миске не хватает еды. Наполняем!");				
				changeSuccessful = changeVolumeCurrent(this.volumeMax - this.volumeCurrent);
				this.volumeCurrent += volumeCurrent;
				}
			} else {
				System.out.println("У кота слишком большой аппетит для этой миски!");
				changeSuccessful = false;
			}
		} else if (volumeCurrent > 0){
			if ((volumeCurrent + this.volumeCurrent) == this.volumeMax) {
				this.volumeCurrent = volumeCurrent;
				changeSuccessful = true;
			} else {
				System.out.println("Превышен максимальный объём....");
				changeSuccessful = false;
			}
		}
		return changeSuccessful;
	}

	/**
	 * Метод устанавливает максимальный объём миски.
	 * @param volumeMax - максимальный объём.
	 */
	public void setVolumeMax (int volumeMax) {
		this.volumeMax = volumeMax;
	}		
	
	
}
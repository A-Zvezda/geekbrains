/*
Хотел создать класс родитель и от него наследоваться, не получилось.
*/
package Lesson_02.exception;

public class MyArrayException extends Exception{
    private int row;
    private int col;
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public MyArrayException() {

    }
    public MyArrayException(String msg, int row, int col) {
        super(msg);
        this.row = row;
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}



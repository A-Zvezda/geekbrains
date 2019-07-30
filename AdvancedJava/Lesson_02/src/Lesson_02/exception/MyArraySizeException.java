package Lesson_02.exception;

public class MyArraySizeException extends Exception{

    private int row;
    private int col;
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public MyArraySizeException(String msg, int row, int col) {
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

package Lesson_02.exception;

public class MyArrayDataException extends Exception{
    private int row;
    private int col;
    private String value;
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public String getValue() {
        return value;
    }

    public MyArrayDataException(String msg, int row, int col, String value) {
        super(msg);
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }


}

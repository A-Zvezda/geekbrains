package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {
    static int dimension = 3;
    static int cellSize = 150;
    static char nullSymbol = '\u0000';
    private char[][] gameField;
    private GameButton[] gameButtons;
    private Game game;
    private boolean gameEnd = false;

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public GameBoard(Game currentGame) {
        this.game = currentGame;
        initField();

    }
    private void initField() {
        setBounds(cellSize *  dimension, cellSize * dimension, 400 , 300);
        setTitle("Крестики-Нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize * dimension,150);

        JPanel gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(new GridLayout(dimension,dimension));
        gameFieldPanel.setSize(dimension * dimension, dimension * dimension);
        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension * dimension];

        for (int i = 0; i < (dimension * dimension); i++ ) {
            GameButton fieldButton = new GameButton(i,this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
        }
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
        setVisible(true);


    }

    void emptyField () {
        for (int i = 0; i < dimension *dimension; i++ ) {
            gameButtons[i].setText("");
            int x = i / GameBoard.dimension;
            int y = i % GameBoard.dimension;

            gameField[x][y] = nullSymbol;
        }
        if (!game.getCurrentPlayer().isRealPlayer()){
            game.parseTurn();
        }
        setGameEnd(false);
    }

    public Game getGame() {
        return game;
    }
    boolean isTrunable(int x, int y) {
        boolean result = false;
        if (gameField[x][y] == nullSymbol) {
            result = true;
        }
        return result;
    }

    void updateGameFiled (int x, int y) {
        gameField[x][y] = game.getCurrentPlayer().getPlayerSign();
    }
    void setGameFiledChar (int x, int y ,char userChar) {
        gameField[x][y] = userChar;
    }

    boolean checkWin() {
        boolean result = false;
        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();
        if (checkTurn(playerSymbol)) {
            result = true;
        }
        return result;
    }

    private boolean checkLine(int start_x, int start_y, int dx, int dy, char sign) {
        for (int i = 0; i < dimension; i++) {
            if (gameField[start_x + i * dx][start_y + i * dy] != sign)
                return false;
        }
        return true;
    }

    public boolean checkWin(char sign) {
        for (int i = 0; i < dimension; i++) {
            // проверяем строки
            if (checkLine(i, 0, 0, 1, sign)) return true;
            // проверяем столбцы
            if (checkLine(0, i, 1, 0, sign)) return true;
        }
        // проверяем диагонали
        if (checkLine(0, 0, 1, 1, sign)) return true;
        if (checkLine(0, dimension - 1, 1, -1, sign)) return true;
        return false;
    }

    boolean checkTurn(char playerSymbol) {
        for (int i = 0; i < dimension; i++) {
            if (gameField[i][0] == playerSymbol && gameField[i][1] == playerSymbol && gameField[i][2]== playerSymbol) {
                return true;
            }
        }
        for (int j = 0; j < dimension; j++) {
            if (gameField[0][j]== playerSymbol && gameField[1][j]== playerSymbol && gameField[2][j]== playerSymbol) {
                return true;
            }
        }
        if (gameField[0][0]== playerSymbol && gameField[1][1]== playerSymbol && gameField[2][2]== playerSymbol) {
            return true;
        }
        if (gameField[0][2]== playerSymbol && gameField[1][1]== playerSymbol && gameField[2][0]== playerSymbol) {
            return true;
        }

        return false;
    }
    boolean isFull() {
        boolean  emptyCellFound = false;
        int i = 0;
        while (i < dimension && !emptyCellFound) {
            int j = 0;
            while (!emptyCellFound && j < dimension) {
                emptyCellFound = gameField[i][j] == nullSymbol;
                j ++;
            }
            i++;
        }

        return emptyCellFound;
    }

    public GameButton getButton(int buttonIndex) {
        return gameButtons[buttonIndex];
    }
    public char getFieldChar (int x, int y) {
        return  gameField[x][y];
    }
    public char getNullSymbol() {
        return nullSymbol;
    }

    public char[][] getGameField() {
        return gameField;
    }
}

package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener (int row, int cell, GameButton gameButton) {
        this.row = row;
        this.cell = cell;
        this.button = gameButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();
        if (board.isTrunable(row,cell) & !board.isGameEnd()) {
            updateByPlayerData(board);
            if (!board.isGameEnd()) {
                if (!board.isFull()) {
                    board.getGame().showMessage("Ничья");
                    board.setGameEnd(true);
                } else {
                    updateByAIData(board);
                }
            }
        } else {
            if (board.isGameEnd()) {
                board.getGame().showMessage("Игра окончена. Начните новую игру!");
            } else {
                board.getGame().showMessage("Некоректный ход");
            }
        }
    }
    private void updateByPlayerData(GameBoard board) {
        if (!board.isGameEnd()) {
            board.updateGameFiled(row, cell);
            button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
            if (board.checkWin()) {
                button.getBoard().getGame().showMessage("Вы выйграли");
                board.setGameEnd(true);
                //board.emptyField();
            } else {
                board.getGame().parseTurn();
            }
        }

    }
    private void updateByAIData (GameBoard board) {
        int x = -1 ,y = -1;
        Random random = new Random();

        boolean ai_win = false;
        boolean user_win = false;
        // Находим выигрышный ход

            for (int i = 0; i < board.dimension; i++) {
                for (int j = 0; j < board.dimension; j++) {
                    if (board.isTrunable(i, j)) {
                        board.updateGameFiled(i, j);
                        if (board.checkWin()) {
                            x = i;
                            y = j;
                            ai_win = true;
                        }

                        board.setGameFiledChar(i, j,board.getNullSymbol());
                    }
                }
            }
        // Блокировка хода пользователя, если он побеждает на следующем ходу
        if (!ai_win) {
                for (int i = 0; i < board.dimension; i++) {
                    for (int j = 0; j < board.dimension; j++) {
                        if (board.isTrunable(i, j)) {
                            board.setGameFiledChar(i, j,'X');
                            if (board.checkWin('X')) {
                                x = i;
                                y = j;
                                user_win = true;
                            }
                            board.setGameFiledChar(i, j,board.getNullSymbol());
                        }
                    }
                }
            }

        if (!ai_win && !user_win) {
            int x1 =-1;
            int y1=-1;
            int maxScore =0;
            char[][] map = board.getGameField();
            for (int i=0; i< board.dimension; i++){
                for (int j=0; j< board.dimension; j++){
                    int score =0;

                    if(board.isTrunable(i, j)){
                        if (i - 1 >= 0 && j - 1 >= 0 && map[i - 1][j - 1] == '0') {
                            score++;
                        }
                        else if (i-1>=0 && map [i-1][j]=='0'){
                            score++;
                        }
                        else if (i-1>=0 && j+1< board.dimension && map [i-1][j+1]=='0') {
                            score++;
                        }
                        else if (j-1>=0 && map [i][j-1]=='0') {
                            score++;
                        }
                        else if (j+1< board.dimension && map [i][j+1]=='0') {
                            score++;
                        }
                        else if (i+1< board.dimension && j-1>=0 && map [i+1][j-1]=='0') {
                            score++;
                        }
                        else if (i+1< board.dimension && map [i+1][j]=='0') {
                            score++;
                        }
                        else if (i+1< board.dimension && j+1< board.dimension && map [i+1][j+1]=='0') {
                            score++;
                        }
                    }
                    if (score>maxScore){
                        maxScore=score;
                        x1=j;
                        y1=i;
                    }
                }
            }
            if (x1!=-1){
                x=x1;
                y=y1;
            }
            if (x == -1) {
            do {
                x = random.nextInt(GameBoard.dimension);
                y = random.nextInt(GameBoard.dimension);
            } while (!board.isTrunable(x, y));
            }
        }

        board.updateGameFiled(x, y);

        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("ИИ выйграл");
            board.setGameEnd(true);
        } else {
            board.getGame().parseTurn();
        }

    }
}

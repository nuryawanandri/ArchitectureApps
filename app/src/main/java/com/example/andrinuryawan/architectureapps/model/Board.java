package com.example.andrinuryawan.architectureapps.model;

import android.util.Log;

import static com.example.andrinuryawan.architectureapps.model.Player.O;
import static com.example.andrinuryawan.architectureapps.model.Player.X;

public class Board {
    private Cell[][] cells = new Cell[3][3];
    private GameState state;
    private Player winner;
    private Player currentPlayer;

    private enum GameState {IN_PROGRESS, FINISHED};

    public Board () {
        restart();
    }

    public void restart () {
        winner = null;
        clearCell();
        currentPlayer = Player.X;
        state = GameState.IN_PROGRESS;
    }

    public void clearCell () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Player markingCell (int row, int col) {
        Player movedPlayer = null;

        if (isValid(row, col)) {
            cells[row][col].setValue(currentPlayer);
            movedPlayer = currentPlayer;

            if (isWinningByPlayer(currentPlayer)) {
                winner = currentPlayer;
                state = GameState.FINISHED;
            } else {
                changeCurrentPlayer();
            }
        }

        return movedPlayer;
    }

    public boolean isWinningByPlayer (Player player) {
        return (
            cells[0][0].getValue() == player && cells[0][1].getValue() == player && cells[0][2].getValue() == player ||
            cells[1][0].getValue() == player && cells[1][1].getValue() == player && cells[1][2].getValue() == player ||
            cells[2][0].getValue() == player && cells[2][1].getValue() == player && cells[2][2].getValue() == player ||
            cells[0][0].getValue() == player && cells[1][0].getValue() == player && cells[2][0].getValue() == player ||
            cells[0][1].getValue() == player && cells[1][1].getValue() == player && cells[2][1].getValue() == player ||
            cells[0][2].getValue() == player && cells[1][2].getValue() == player && cells[2][2].getValue() == player ||
            cells[0][0].getValue() == player && cells[1][1].getValue() == player && cells[2][2].getValue() == player ||
            cells[2][0].getValue() == player && cells[1][1].getValue() == player && cells[0][2].getValue() == player
        );
    }

    public boolean isValid (int row, int col) {
        if (state == GameState.FINISHED) {
            return false;
        } else if (isCellNotEmpty(row, col)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isCellNotEmpty (int row, int col) {
        return cells[row][col].getValue() != null;
    }


    public void changeCurrentPlayer () {
        currentPlayer = currentPlayer == X ? O : X;
    }

    public Player getWinner () {
        return winner;
    }

}

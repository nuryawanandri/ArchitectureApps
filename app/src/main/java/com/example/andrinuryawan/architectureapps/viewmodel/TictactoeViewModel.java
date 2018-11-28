package com.example.andrinuryawan.architectureapps.viewmodel;


import android.util.Log;

import com.example.andrinuryawan.architectureapps.model.Board;
import com.example.andrinuryawan.architectureapps.model.Player;

public class TictactoeViewModel {

    private Board boardModel;

    public TictactoeViewModel() {
        boardModel = new Board();
    }

    public String markingCell (int row, int col) {
        Player playerMoved = boardModel.markingCell(row, col);
        if (playerMoved != null) {
            return playerMoved.toString();
        } else {
            return null;
        }
    }

    public String getWinner () {
        if (boardModel.getWinner() != null) {
            return boardModel.getWinner().toString();
        } else {
            return null;
        }

    }

    public void onResetTheGame () {
        boardModel.restart();
    }
}

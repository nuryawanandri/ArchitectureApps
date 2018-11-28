package com.example.andrinuryawan.architectureapps.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.andrinuryawan.architectureapps.R;
import com.example.andrinuryawan.architectureapps.model.Player;
import com.example.andrinuryawan.architectureapps.viewmodel.TictactoeViewModel;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private GridLayout gridBoard;
    private TictactoeViewModel tictactoeViewModel = new TictactoeViewModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.playerWinDesc);
        gridBoard = findViewById(R.id.board);
    }

    public void markButton(View view) {
        button = (Button) view;

        String buttonTag = (String) button.getTag();
        String row, col;
        row = buttonTag.substring(0, 1);
        col = buttonTag.substring(1,2);

        String currentPlayer = tictactoeViewModel.markingCell(Integer.parseInt(row), Integer.parseInt(col));

        if (currentPlayer != null) {
            button.setText(currentPlayer);
        }

//        Intent intent;
//        intent = new Intent(this, WinnerActivity.class);
//        intent.putExtra("msg", "Winner");
//
//        startActivity(intent);

        String winner = tictactoeViewModel.getWinner();
        if (winner != null) {
            textView.setText("winner : " + winner);
        }

    }

    public void onReset (View view) {
        tictactoeViewModel.onResetTheGame();

        for (int i = 0; i < gridBoard.getChildCount(); i++) {
            ((Button) gridBoard.getChildAt(i)).setText("");
        }

        textView.setText("");
    }
}

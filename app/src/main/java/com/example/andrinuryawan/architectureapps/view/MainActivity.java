package com.example.andrinuryawan.architectureapps.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrinuryawan.architectureapps.R;
import com.example.andrinuryawan.architectureapps.model.Player;
import com.example.andrinuryawan.architectureapps.viewmodel.TictactoeViewModel;
import com.example.andrinuryawan.architectureapps.viewmodel.retrofit.RetrofitClientInstance;
import com.example.andrinuryawan.architectureapps.viewmodel.retrofit.User;
import com.example.andrinuryawan.architectureapps.viewmodel.retrofit.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private GridLayout gridBoard;
    private TictactoeViewModel tictactoeViewModel = new TictactoeViewModel();
    private TextView player1;
    private TextView player2;

    private String player1name;
    private String player2name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.playerWinDesc);
        gridBoard = findViewById(R.id.board);

        player1name = getIntent().getStringExtra("player1name");
        player2name = getIntent().getStringExtra("player2name");

        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);

        player1.setText(player1name);
        player2.setText(player2name);
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

        String winner = tictactoeViewModel.getWinner();
        if (winner != null) {
            if (winner.equals("X")) {
                textView.setText("Winner : " + player1name);
            } else {
                textView.setText("Winner : " + player2name);
            }

        }

        if (tictactoeViewModel.isTheGameDraw()) {
            textView.setText("DRAW!!!");
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

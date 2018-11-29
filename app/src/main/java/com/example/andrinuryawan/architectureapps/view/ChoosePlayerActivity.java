package com.example.andrinuryawan.architectureapps.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.andrinuryawan.architectureapps.R;
import com.example.andrinuryawan.architectureapps.viewmodel.retrofit.RetrofitClientInstance;
import com.example.andrinuryawan.architectureapps.viewmodel.retrofit.User;
import com.example.andrinuryawan.architectureapps.viewmodel.retrofit.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChoosePlayerActivity extends AppCompatActivity {

    private Spinner player1;
    private Spinner player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);

        player1 = findViewById(R.id.playerX);
        player2 = findViewById(R.id.playerO);

        UserService service = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
        Call<List<User>> call = service.getAllUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                initializePlayer(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(ChoosePlayerActivity.this, "Try Again Later...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initializePlayer (List<User> users) {
        List<String> list = new ArrayList<>();

        for (User user : users) {
            list.add(user.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player1.setAdapter(dataAdapter);
        player2.setAdapter(dataAdapter);
    }

    public void onSubmitPlayer (View view) {
        Intent intent = new Intent(this, MainActivity.class);

        String player1name = player1.getSelectedItem().toString();
        String player2name = player2.getSelectedItem().toString();

        intent.putExtra("player1name", player1name);
        intent.putExtra("player2name", player2name);

        if (player1name.equals(player2name)) {
            Toast.makeText(ChoosePlayerActivity.this, "Player 2 already used for Player 1", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(intent);
        }
    }
}

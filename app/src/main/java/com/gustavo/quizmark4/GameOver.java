package com.gustavo.quizmark4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    private Button btnReturn;
    private TextView txtScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        final Intent intent = getIntent();
        final int playerScore = intent.getIntExtra(QuizGame.EXTRA_SCORE, -1);

        txtScore = findViewById(R.id.score);
        txtScore.setText(Integer.toString(playerScore));

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent1);
        });
    }

    @Override
    public void onBackPressed() {
    }
}
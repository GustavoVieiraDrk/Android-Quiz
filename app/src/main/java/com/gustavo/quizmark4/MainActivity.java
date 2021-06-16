package com.gustavo.quizmark4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
//    private List<Integer> questionIndex = new ArrayList<>(5);
    private int[] questionIndex = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        for (int count = 0; count < 5; count++){
            questionIndex[count] = new Random().nextInt(10);
        }

        for (int count = 0; count < 5; count++){
            for (int j = count + 1; j < 5; j++){
                if (questionIndex[count] == questionIndex[j]){
                    questionIndex[j] = new Random().nextInt(10);
                    j = count;
                }
            }
        }

//        final Random random = new Random();
//        questionIndex.clear();
//        for (int i = 0; i < 5; i += 1) {
//            int index;
//            while (true) {
//                index = random.nextInt(5);
//                if (!questionIndex.contains(index)) {
//                    questionIndex.add(index);
//                    break;
//                }
//            }
//        }

        findViewById(R.id.btnStart).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), QuizGame.class)
                    .putExtra(QuizGame.EXTRA_QUESTION_INDEX, questionIndex)
                    .putExtra(QuizGame.EXTRA_SCORE, 0)
                    .putExtra(QuizGame.EXTRA_QUESTION_COUNTER, 0);
            startActivity(intent);
        });
    }
}


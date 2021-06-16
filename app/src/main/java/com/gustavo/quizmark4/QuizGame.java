package com.gustavo.quizmark4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizGame extends AppCompatActivity {
    public static final String EXTRA_QUESTION_INDEX = "question_index";
    public static final String EXTRA_INSTANCE = "instance";
    public static final String EXTRA_SCORE = "score";
    public static final String EXTRA_QUESTION_COUNTER = "question_counter";
    private int[] questionIndex = new int[5];
    private static final int DEFAULT_INT_VALUE = -1;
    private static final int TOTAL_QUESTIONS = 5;
    private static final int REQUEST_CODE_NEXT_QUESTION = 42;

    private QuestionShape currentQuestion;
//    private List<QuestionShape> currentQuestions;
    private RadioGroup radioGroup;
    private int questionCounter, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);
        setResult(Activity.RESULT_CANCELED);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        final Intent intent = getIntent();
        questionIndex = intent.getIntArrayExtra(EXTRA_QUESTION_INDEX);
        score = intent.getIntExtra(EXTRA_SCORE, DEFAULT_INT_VALUE);
        questionCounter = intent.getIntExtra(EXTRA_QUESTION_COUNTER, DEFAULT_INT_VALUE);
//        currentQuestions = intent.getParcelableArrayListExtra(EXTRA_QUESTION_COUNTER);

        currentQuestion = QuestionsRepository.getInstance().getNextQuestion(questionIndex[questionCounter]);
//        currentQuestions.add(currentQuestion);

        setupView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NEXT_QUESTION && resultCode == Activity.RESULT_CANCELED) {
            if (data == null) return;
            questionIndex = data.getIntArrayExtra(EXTRA_QUESTION_INDEX);
            questionCounter = data.getIntExtra(EXTRA_QUESTION_COUNTER, questionCounter);
            score = data.getIntExtra(EXTRA_SCORE, score);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    public void onBackPressed() {
        showPreviousQuestion();
    }

    private void setupView() {
        Button btnNext = findViewById(R.id.btnNext);
        Button btnBack = findViewById(R.id.btnBack);
        TextView txtQuestion = findViewById(R.id.question);
        radioGroup = findViewById(R.id.btnRadioGroup);
        RadioButton rb1 = findViewById(R.id.btnA);
        RadioButton rb2 = findViewById(R.id.btnB);
        RadioButton rb3 = findViewById(R.id.btnC);
        RadioButton rb4 = findViewById(R.id.btnD);

        txtQuestion.setText(currentQuestion.getQuestion());
        rb1.setText(currentQuestion.getA());
        rb2.setText(currentQuestion.getB());
        rb3.setText(currentQuestion.getC());
        rb4.setText(currentQuestion.getD());

        btnNext.setOnClickListener(v -> {
            if (!computeAnswer()) return;

            if (questionCounter >= TOTAL_QUESTIONS) {
                showGameOver();
                return;
            }

            showNextQuestion();
        });
        btnBack.setOnClickListener(v -> onBackPressed()); // reCall function onBackPressed
    }

    private boolean computeAnswer() {
        final int selectedButtonId = radioGroup.getCheckedRadioButtonId();
        switch (selectedButtonId) {
            case R.id.btnA:
                if (currentQuestion.getCorrectAnswer() == 1) score += 1;
                break;
            case R.id.btnB:
                if (currentQuestion.getCorrectAnswer() == 2) score += 1;
                break;
            case R.id.btnC:
                if (currentQuestion.getCorrectAnswer() == 3) score += 1;
                break;
            case R.id.btnD:
                if (currentQuestion.getCorrectAnswer() == 4) score += 1;
                break;
            default:
                return false;
        }

        questionCounter += 1;

        return true;
    }

    private void showNextQuestion() {
        final Intent intentNextQuestion = new Intent(getApplicationContext(), QuizGame.class)
                .putExtra(EXTRA_QUESTION_INDEX, questionIndex)
                .putExtra(EXTRA_SCORE, score)
                .putExtra(EXTRA_QUESTION_COUNTER, questionCounter);
        startActivityForResult(intentNextQuestion, REQUEST_CODE_NEXT_QUESTION);
    }

    private void showGameOver() {
        final Intent intentGameOver = new Intent(getApplicationContext(), GameOver.class)
                .putExtra(EXTRA_SCORE, score);
        startActivity(intentGameOver);
    }

    public void showPreviousQuestion() {
        if (score != 0){
            score = score - 1;
        }
        questionCounter -= 1;
        setResult(Activity.RESULT_CANCELED, new Intent()
                .putExtra(EXTRA_QUESTION_INDEX, questionIndex)
                .putExtra(EXTRA_QUESTION_COUNTER, questionCounter)
                .putExtra(EXTRA_SCORE, score)
        );
        super.onBackPressed();
    }
}
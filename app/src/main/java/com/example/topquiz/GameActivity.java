package com.example.topquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import model.Question;
import model.QuestionBank;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mQuestionTextView;
    private Button  mButton1, mButton2, mButton3, mButton4;
    private final QuestionBank mQuestionBank = createQuestionBank();
    private int mRemainingQuestionCount, mScore;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    private QuestionBank createQuestionBank()
    {
        Question question1 = new Question(
                "Qui est le créateur d'Android ?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Woznik",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );

        Question question2 = new Question(
                "Quel est le surnom d'Oscar ?",
                Arrays.asList(
                        "Slip",
                        "String",
                        "Chaussette",
                        "Socket"
                ),
                2
        );

        Question question3 = new Question(
                "Quel est le numéro de la maison des Simpsons ?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"
                ),
                3
        );

        return new QuestionBank(Arrays.asList(question1,question2,question3));
    }
    private void displayQuestion(final Question question)
    {
        mQuestionTextView.setText(question.getQuestion().toString());
        mButton1.setText(question.getChoiceList().get(0));
        mButton2.setText(question.getChoiceList().get(1));
        mButton3.setText(question.getChoiceList().get(2));
        mButton4.setText(question.getChoiceList().get(3));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mQuestionTextView = findViewById(R.id.game_activity_textview_question);
        mButton1 = findViewById(R.id.game_activity_button_1);
        mButton2 = findViewById(R.id.game_activity_button_2);
        mButton3 = findViewById(R.id.game_activity_button_3);
        mButton4 = findViewById(R.id.game_activity_button_4);
        mRemainingQuestionCount = 3;
        mScore = 0 ;

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        displayQuestion(mQuestionBank.getNextQuestion());
    }
    @Override
    public void onClick(View view) {
        int index;

        if( view == mButton1)
        {
            index = 0;
        } else if (view == mButton2)
        {
            index = 1;
        } else if (view == mButton3)
        {
            index = 2;
        } else if (view == mButton4)
        {
            index = 3;
        } else
        {
            throw new IllegalStateException("Unknown clicked view : "+ view);
        }
        if (mQuestionBank.getCurrentQuestion().getAnswerIndex() == index)
        {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore += 10;
        } else {
            Toast.makeText(this, "Faux!", Toast.LENGTH_SHORT).show();
        }
        mRemainingQuestionCount--;
        if (mRemainingQuestionCount > 0)
        {
            displayQuestion(mQuestionBank.getNextQuestion());
        } else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bien joué!").setMessage("Ton score est de "+ mScore).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent();
                    intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }).create().show();
        }
    }
}
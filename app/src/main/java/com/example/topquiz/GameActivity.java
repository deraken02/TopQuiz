package com.example.topquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import model.Question;
import model.QuestionBank;

public class GameActivity extends AppCompaActivity {
    private TextView mQuestionTextView;
    private Button  mButton1, mButton2, mButton3, mButton4;
    private final QuestionBank QuestionBank = createQuestionBank();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mQuestionTextView = findViewById(R.id.game_activity_textview_question);
        mButton1 = findViewById(R.id.game_activity_button_1);
        mButton2 = findViewById(R.id.game_activity_button_2);
        mButton3 = findViewById(R.id.game_activity_button_3);
        mButton4 = findViewById(R.id.game_activity_button_4);
    }
}
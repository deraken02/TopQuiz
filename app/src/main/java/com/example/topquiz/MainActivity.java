package com.example.topquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import model.User;

public class MainActivity extends AppCompatActivity {
    private TextView mGreetingTextView;
    private EditText mNameEditText;
    private Button mPlayButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGreetingTextView = findViewById(R.id.main_textview_greeting);
        mNameEditText = findViewById(R.id.main_edittext_name);
        mPlayButton = findViewById(R.id.main_button_play);
        mPlayButton.setEnabled(false); //Désactive le bouton
        User mUser = new User();
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPlayButton.setEnabled(!editable.toString().isEmpty()); //attend que l'utilisateur tape au moins une lettre pour réactiver le bouton
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //L'utilisateur vient de cliquer
                mUser.setFirstName(mNameEditText.getText().toString());
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class); //Prépare l'activité
                startActivity(gameActivityIntent); //Lance l'activité
            }

        });
    }
}
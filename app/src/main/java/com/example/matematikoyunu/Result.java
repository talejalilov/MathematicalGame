package com.example.matematikoyunu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Result extends AppCompatActivity {
    TextView result,recordScore;
    Button playAgain , exit;
    int newscore = 0;
    int rekorScore = 0;
    SharedPreferences setting ;
    MediaPlayer playAgn,ext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        playAgn = MediaPlayer.create(this,R.raw.tryagain);
        ext = MediaPlayer.create(this,R.raw.extbtn);

        recordScore = findViewById(R.id.textViewRecord);
        result = findViewById(R.id.textViewResult);
        playAgain = findViewById(R.id.btnAgain);
        exit = findViewById(R.id.btnExit);

        Intent intent = getIntent();
        newscore =  intent.getIntExtra("score",0);
        String userScore = String.valueOf(newscore);
        result.setText("Your Score: " + userScore);

        setting = getSharedPreferences("Game_data",MODE_PRIVATE);
        rekorScore = setting.getInt("HighScore",0);

        if(newscore>=rekorScore) {
            recordScore.setText("High Score: " + newscore );

            //Save
            SharedPreferences.Editor editor = setting.edit();
            editor.putInt("HighScore",newscore);
            editor.apply();
        }else{
            recordScore.setText("High Score: " + rekorScore);
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgn.start();
                Intent intent = new Intent(Result.this,MainActivity.class);
                startActivity(intent);
                Animatoo.animateInAndOut(Result.this);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ext.start();
                Animatoo.animateSpin(Result.this);
                finish();
            }
        });
    }
}
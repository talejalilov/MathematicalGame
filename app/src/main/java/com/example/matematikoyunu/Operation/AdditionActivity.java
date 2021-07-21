package com.example.matematikoyunu.Operation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.matematikoyunu.R;
import com.example.matematikoyunu.Result;

import java.util.Locale;
import java.util.Random;

public class AdditionActivity extends AppCompatActivity {

    private TextView scoreText, lifeText, timeText, questionText;
    private EditText editAnswer;
    private Button okayBtn, nextBtn;
    private MediaPlayer okaybtn, passbtn;

    Random random = new Random();
    int number1 = 0;
    int number2 = 0;

    int userAnswer;
    int realAnswer;
    int userLife = 3;
    int userScore = 0;

    CountDownTimer timer;
    private static final long START_TIMER_MILLS = 60000;
    boolean timerRunning;
    long time_left_in_mills = START_TIMER_MILLS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        okaybtn = MediaPlayer.create(this, R.raw.okaybtn);
        passbtn = MediaPlayer.create(this, R.raw.bolme);

        scoreText = findViewById(R.id.textViewScore);
        lifeText = findViewById(R.id.textViewLife);
        timeText = findViewById(R.id.textViewTime);
        questionText = findViewById(R.id.textViewQuestion);
        editAnswer = findViewById(R.id.editTextAnswer);
        okayBtn = findViewById(R.id.btnOkay);
        nextBtn = findViewById(R.id.btnNext);

        gameContinue();
        startTimer();

        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okaybtn.start();

                if (!editAnswer.getText().toString().matches("")) {//Eger bos tiklanirsa
                    String editTxt = editAnswer.getText().toString();
                    userAnswer = Integer.parseInt(editTxt);

                    if (userAnswer == realAnswer) {
                        userScore = userScore + 10;
                        scoreText.setText("" + userScore);

                        for (int i=1;i<100 ; i++){
                        if (userScore == (i)*10) {
                            Toast.makeText(AdditionActivity.this, " CONGRATULATIONS!!! ", Toast.LENGTH_LONG).show();
                            pauseTimer();
                            resetTimer();
                            startTimer();
                            userLife = 3;
                            lifeText.setText("" + userLife);}
                        }
                    } else {
                        userLife = userLife - 1;
                        lifeText.setText("" + userLife);
                        if (userLife == 0) {
                            pauseTimer();
                            Toast.makeText(AdditionActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AdditionActivity.this, Result.class);
                            intent.putExtra("score", userScore);
                            startActivity(intent);
                            Animatoo.animateDiagonal(AdditionActivity.this);
                            finish();
                        }
                    }

                    gameContinue();
                    editAnswer.setText("");
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passbtn.start();
                editAnswer.setText("");
                gameContinue();
            }
        });
    }

    public void gameContinue() {
    for (int i=0;i<100;i++){

        if(userScore>=i*100 && userScore< (i+1)*100){
            number1 = random.nextInt((i+1)*50)+5*i;
            number2 = random.nextInt((i+1)*50)+5*i;
        }
    }
        realAnswer = number1 + number2;

        questionText.setText(number1 + " + " + number2);
    }
    public void startTimer() {

        timer = new CountDownTimer(time_left_in_mills, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_mills = millisUntilFinished;
                updateText();
            }
            @Override
            public void onFinish() {
                timerRunning = false;
                pauseTimer();

                Toast.makeText(AdditionActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdditionActivity.this, Result.class);
                intent.putExtra("score", userScore);
                startActivity(intent);
                Animatoo.animateShrink(AdditionActivity.this);
                finish();
            }
        }.start();

        timerRunning = true;
    }

    private void updateText() {

        int second = (int) ((time_left_in_mills / 1000) % 60);
        String time_left = String.format(Locale.getDefault(), "%02d", second);

        timeText.setText(time_left);
    }

    private void pauseTimer() {

        timer.cancel();
        timerRunning = false;
    }

    public void resetTimer() {

        time_left_in_mills = START_TIMER_MILLS;
        updateText();

    }


}
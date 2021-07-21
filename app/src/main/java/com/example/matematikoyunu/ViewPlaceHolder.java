package com.example.matematikoyunu;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewPlaceHolder {

    TextView scoreText, lifeText, timeText, questionText;
    EditText editAnswer;
    Button okayBtn, nextBtn;

    public ViewPlaceHolder(View view){

        scoreText = view.findViewById(R.id.textViewScore);
        lifeText = view.findViewById(R.id.textViewLife);
        timeText = view.findViewById(R.id.textViewTime);
        questionText = view.findViewById(R.id.textViewQuestion);
        editAnswer = view.findViewById(R.id.editTextAnswer);
        okayBtn = view.findViewById(R.id.btnOkay);
        nextBtn = view.findViewById(R.id.btnNext);
    }


}

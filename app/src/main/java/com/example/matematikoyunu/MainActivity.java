package com.example.matematikoyunu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.matematikoyunu.Operation.AdditionActivity;
import com.example.matematikoyunu.Operation.DividedBy;
import com.example.matematikoyunu.Operation.Multiply;
import com.example.matematikoyunu.Operation.Subtraction;

public class MainActivity extends AppCompatActivity   {
    MediaPlayer plus,minus,vurma,bolme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus  = MediaPlayer.create(this,R.raw.addition);
        minus = MediaPlayer.create(this,R.raw.minus);
        vurma = MediaPlayer.create(this,R.raw.vurma);
        bolme = MediaPlayer.create(this,R.raw.vurma);

    }

    public void mainBtn(View view) {
        if(view==findViewById(R.id.toplamaBtn)) {
            plus.start();
            startActivity(new Intent(MainActivity.this, AdditionActivity.class));
            Animatoo.animateSplit(this);
            finish();


        }
        if(view==findViewById(R.id.cikarmaBtn)) {
            minus.start();
            startActivity(new Intent(MainActivity.this, Subtraction.class));
            Animatoo.animateSplit(this);
            finish();

        }
        if(view==findViewById(R.id.carpmaBtn)) {
            vurma.start();
            startActivity(new Intent(MainActivity.this, Multiply.class));
            Animatoo.animateSplit(this);
            finish();
        }
        if(view==findViewById(R.id.bolme)) {
            bolme.start();
            startActivity(new Intent(MainActivity.this, DividedBy.class));
            Animatoo.animateSplit(this);
            finish();

        }

    }



}

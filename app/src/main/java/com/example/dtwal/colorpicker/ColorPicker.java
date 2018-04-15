package com.example.dtwal.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.dtwal.colorpicker.User.genre01;

public class ColorPicker extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar SeekA, SeekR,SeekG,SeekB;
    Button saveColorButton, cancelColorButton;
    TextView ShowColor;
    Integer r, g, b;
    static int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_color_picker);
        SeekA=findViewById(R.id.seekA);
        SeekR=findViewById(R.id.seekR);
        SeekG=findViewById(R.id.seekG);
        SeekB=findViewById(R.id.seekB);
        ShowColor=findViewById(R.id.textView);
        saveColorButton = findViewById(R.id.saveColorButton);
        cancelColorButton = findViewById(R.id.cancelColorButton);

        //This activity implements SeekBar OnSeekBarChangeListener
        SeekA.setOnSeekBarChangeListener(this);
        SeekR.setOnSeekBarChangeListener(this);
        SeekG.setOnSeekBarChangeListener(this);
        SeekB.setOnSeekBarChangeListener(this);
    }
    //Satisfy the implements
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
        //get current ARGB values
        int A=SeekA.getProgress();
        int R=SeekR.getProgress();
        int G=SeekG.getProgress();
        int B=SeekB.getProgress();
        //Reference the value changing
        int id=seekBar.getId();
        //Get the chnaged value
        if(id == com.example.dtwal.colorpicker.R.id.seekA)
            A=progress;
        else if(id == com.example.dtwal.colorpicker.R.id.seekR)
            R=progress;
        else if(id == com.example.dtwal.colorpicker.R.id.seekA)
            G=progress;
        else if(id == com.example.dtwal.colorpicker.R.id.seekA)
            B=progress;
        //Build and show the new color
        ShowColor.setBackgroundColor(Color.argb(A,R,G,B));
        //show the color value
        ShowColor.setText("0x"+String.format("%02x", A)+String.format("%02x", R)
                +String.format("%02x", G)+String.format("%02x", B));
        //some math so text shows (needs improvement for greys)
        ShowColor.setTextColor(Color.argb(0xff,255-R,255-G,255-B));

        cancelColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                r = SeekR.getProgress();
                g = SeekG.getProgress();
                b = SeekB.getProgress();
                int c = ShowColor.getSolidColor();

                finish();
            }
        });
    }
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Only required due to implements
    }
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Only required due to implements

    }
}

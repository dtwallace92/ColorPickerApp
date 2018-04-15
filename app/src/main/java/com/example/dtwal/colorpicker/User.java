package com.example.dtwal.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class User extends AppCompatActivity {

    static TextView genre01, genre02, genre03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        genre01 = findViewById(R.id.genre01TextView);
        genre02 = findViewById(R.id.genre02TextView);
        genre03 = findViewById(R.id.genre03TextView);

        genre01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(User.this, ColorPicker.class);
                startActivity(j);

                //onActivityResult();
            }
        });
    }

    public User () {

    }

    public static void changeBackground(TextView a, int r, int g, int b) {
        a.setBackgroundColor(Color.rgb(r, g, b));
        return;

    }

    public interface MyCallBack {
        public void UpdateMyColor(int color);
    }

    MyCallBack myCallBack = null;
    public User(MyCallBack callBack) {
        this.myCallBack=callBack;
    }
}

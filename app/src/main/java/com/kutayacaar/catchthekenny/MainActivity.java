package com.kutayacaar.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
TextView scoreText;
TextView timeText;
int score;
ImageView imageView;
ImageView imageViewa;
    ImageView imageViewb;
    ImageView imageViewc;
    ImageView imageViewd;
    ImageView imageViewe;
    ImageView imageViewf;
    ImageView imageViewg;
    ImageView imageViewk;
ImageView[] imageArray;
Handler handler;
Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
scoreText=findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);
        imageView = findViewById(R.id.imageView);
        imageViewa = findViewById(R.id.imageViewa);
        imageViewb = findViewById(R.id.imageViewb);
        imageViewc = findViewById(R.id.imageViewc);
        imageViewd = findViewById(R.id.imageViewd);
        imageViewe = findViewById(R.id.imageViewe);
        imageViewf = findViewById(R.id.imageViewf);
        imageViewg = findViewById(R.id.imageViewg);
        imageViewk = findViewById(R.id.imageViewk);
        imageArray= new ImageView[]
                {imageView,imageViewa,imageViewb,imageViewc,imageViewd,imageViewe,imageViewf,imageViewg,imageViewk};

        hideImages();
    score = 0 ;


        new CountDownTimer(15000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }


                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart?    ");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //restart

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

            }
        }.start();
    }

    public void increasescore(View view){
        score++;
        scoreText.setText("Score: "+  score);

}
public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() { for (ImageView image : imageArray){
                image.setVisibility(View.INVISIBLE);
            }
                Random random = new Random ();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);

            }
        };

handler.post(runnable);
}

}
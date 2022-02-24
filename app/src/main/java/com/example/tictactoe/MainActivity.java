package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 -> X
    //1 -> O
    //2 -> Null(Blank)
    MediaPlayer m1,m2;
    int count = 0;
    boolean gameActive = true;
    TextView textView;
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                             {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                              {0, 4, 8}, {2, 4, 6}};
    public void playerTap(View view){
        textView = findViewById(R.id.textView);
        m1 = MediaPlayer.create(this,R.raw.tap);
        m2 = MediaPlayer.create(this,R.raw.win);
        m1.start();
        count += 1;
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(view.getTag().toString());
        if (!gameActive || count > 9){
            gameReset(view);
        }
        if (gameState[tappedImage] == 2){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0){
                activePlayer = 1;
                img.setImageResource(R.drawable.x);
                textView.setText("O's Turn");
            }
            else{
                activePlayer = 0;
                img.setImageResource(R.drawable.o);
                textView.setText("X's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //Checking if someone has won.
        for (int[] winPosition:winPositions){
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2){
                gameActive = false;
                if (gameState[winPosition[0]] == 0){
                    m2.start();
                    textView.setText("X has won!");
                }
                else{
                    m2.start();
                    textView.setText("O has won!");
                }
            }
        }
    }
    public void gameReset(View view){
        count = 0;
        gameActive = true;
        activePlayer = 0;
        for (int i = 0;i<gameState.length;i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        textView.setText("X's Turn");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
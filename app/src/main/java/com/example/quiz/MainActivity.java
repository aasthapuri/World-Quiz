package com.example.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private Game game;
    private String question;
    private String answer;
    private int score;
    private int qNum;
    private String log;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.game = new Game();
        ask();
        this.score = 0;
        this.qNum = 1;
        this.log = "";
    }

    private void ask()
    {
        String qa = game.qa();
        String[] pieces = qa.split("[\\n]+");
        this.question = pieces[0];
        this.answer = pieces[1];

        ((TextView) findViewById(R.id.question)).setText(this.question);
    }

    public void onDone(View v)
    {
        if (qNum >= 11)
        {
            this.finish();
        }
        else
        {
            String userAnswer = ((EditText) findViewById(R.id.answer)).getText().toString().trim();
            if (userAnswer.equalsIgnoreCase(this.answer))
            {
                this.score ++;
            }
            String tempLog;
            tempLog = "Q# " + this.qNum + ": " + this.question + "\n";
            tempLog += "Your answer: " + userAnswer.toUpperCase() + "\n";
            tempLog += "Correct Answer: " + this.answer + "\n";

            this.log = tempLog + "\n" + this.log;



            this.qNum++;
            String scoreString = "Score " + this.score;
            ((TextView) findViewById(R.id.score)).setText(scoreString);
            ((TextView) findViewById(R.id.log)).setText(this.log);

            if(qNum >= 11)
            {
                String gameOver = "GAME OVER!";
                ((TextView) findViewById(R.id.qNum)).setText(gameOver);
                ((Button) findViewById(R.id.done)).setEnabled(false);
                ((TextView) findViewById(R.id.answer)).setText(" ");
            }
            else
            {
                String qNumString = "Q# " + this.qNum;
                ((TextView) findViewById(R.id.qNum)).setText(qNumString);
                ask();
            }
            ((TextView) findViewById(R.id.answer)).setText(" ");

        }
    }
}

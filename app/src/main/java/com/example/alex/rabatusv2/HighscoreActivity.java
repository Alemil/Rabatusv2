package com.example.alex.rabatusv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Set;

/**
 * Created by Mathias Linde on 18-06-2015.
 */
public class HighscoreActivity extends Activity {
    //temp
    final Random r = new Random();
    private String newName;
    int[] scoreList = new int[10];
    String[] nameList = new String[10];

    private final String HIGHSCORE_NAMES_KEY = "HighscoreNames";
    private final String HIGHSCORE_SCORES_KEY = "HighscoreScores";

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_activity);
        //temp
        Button temp = (Button) findViewById(R.id.temp_button);
        Button tempReset = (Button) findViewById(R.id.temp_reset);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String restoredNames = prefs.getString(HIGHSCORE_NAMES_KEY, null);
        String restoredScores = prefs.getString(HIGHSCORE_SCORES_KEY,null);

        if(restoredNames != null && restoredScores != null){
                nameList = restoredNames.split("!",10);
                String[] scoreTempList = restoredScores.split("!",10);
                for(int i = 0 ; i < scoreTempList.length ; i++){
                    try {
                        scoreList[i] = Integer.parseInt(scoreTempList[i]);
                    }catch (NumberFormatException e){
                        scoreList[i] = 0;
                    }
                    updateListView(i,nameList[i],scoreList[i]);
                }
        }

        tempReset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Log.v("highscore","Everything worked untill now1");
                for(int i = 0; i < 10 ; i++){
                    nameList[i] = " ";
                    scoreList[i] = 0;


                    updateListView(i,nameList[i],scoreList[i]);
                    prefs.edit().putString(HIGHSCORE_SCORES_KEY, null);
                    prefs.edit().putString(HIGHSCORE_NAMES_KEY,null);
                }
            }
        });
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = r.nextInt(100);
                makePopup(score);

            }
        });
    }

    public void makePopup(final int score){
        AlertDialog.Builder alert = new AlertDialog.Builder(HighscoreActivity.this);

        final EditText editText = new EditText(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        editText.setLayoutParams(lp);
        editText.setText("AAAA");

        alert.setMessage("Write your name, must be 4 digits");
        alert.setTitle("You scored " + score + " points!");

        alert.setView(editText);
        alert.setCancelable(false);


        alert.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String writtenText = String.valueOf(editText.getText());

                if(writtenText.length() != 4){
                    Toast.makeText(getApplicationContext(),"Not the right amount of letters!", Toast.LENGTH_SHORT).show();
                    makePopup(score);
                }else{

                    updateList(writtenText,score);

                }
            }
        });

        alert.show();
    }

    public void updateList(String name, int score){

        if(score >= scoreList[9] ){
            int lowestplace = 9;

            int j = 0;
            while(j <= 9) {
                if (score >= scoreList[j]) {
                    lowestplace = j;
                    break;
                } else {
                    j++;
                }
            }
            String scoreString=""+scoreList[0];
            String nameString =nameList[0];
            int tempScore;
            String tempName;
            for(int i = lowestplace ; i < 10 ; i++){
                tempScore = scoreList[i];
                tempName = nameList[i];

                scoreList[i] = score;
                nameList[i] = name;

                score=tempScore;
                name=tempName;

                updateListView(i,nameList[i],scoreList[i]);

                if(i > 0 ){
                    scoreString += "!" + scoreList[i];
                    nameString += "!" + nameList[i];
                }
            }

            Log.v("highscore","Everything worked untill now2");
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

            sharedPreferences.edit().putString(HIGHSCORE_NAMES_KEY, nameString).apply();
            sharedPreferences.edit().putString(HIGHSCORE_SCORES_KEY, scoreString).apply();

        }
    }

    public void updateListView(int i, String name, int score){

        TextView nameView ;
        TextView scoreView;

        switch ( i ){
            case 0:
                nameView = (TextView) findViewById(R.id.first_name);
                scoreView = (TextView) findViewById(R.id.first_score);

                nameView.setText(name);
                scoreView.setText("" + score);

                break;

            case 1:

                nameView = (TextView) findViewById(R.id.sec_name);
                scoreView = (TextView) findViewById(R.id.sec_score);

                nameView.setText(name);
                scoreView.setText("" + score);

                break;

            case 2:

                nameView = (TextView) findViewById(R.id.third_name);
                scoreView = (TextView) findViewById(R.id.third_score);

                nameView.setText(name);
                scoreView.setText("" + score);

                break;

            case 3:

                nameView = (TextView) findViewById(R.id.fourth_name);
                scoreView = (TextView) findViewById(R.id.fourth_score);

                nameView.setText(name);
                scoreView.setText("" + score);

                break;

            case 4:

                nameView = (TextView) findViewById(R.id.fifth_name);
                scoreView = (TextView) findViewById(R.id.fifth_score);

                nameView.setText(name);
                scoreView.setText("" + score);


                break;

            case 5:

                nameView = (TextView) findViewById(R.id.sixth_name);
                scoreView = (TextView) findViewById(R.id.sixth_score);

                nameView.setText(name);
                scoreView.setText("" + score);

                break;

            case 6:

                nameView = (TextView) findViewById(R.id.seven_name);
                scoreView = (TextView) findViewById(R.id.seven_score);

                nameView.setText(name);
                scoreView.setText("" + score);


                break;

            case 7:

                nameView = (TextView) findViewById(R.id.eight_name);
                scoreView = (TextView) findViewById(R.id.eight_score);

                nameView.setText(name);
                scoreView.setText("" + score);


                break;

            case 8:

                nameView = (TextView) findViewById(R.id.ninth_name);
                scoreView = (TextView) findViewById(R.id.ninth_score);

                nameView.setText(name);
                scoreView.setText("" + score);

                break;

            case 9:

                nameView = (TextView) findViewById(R.id.tenth_name);
                scoreView = (TextView) findViewById(R.id.tenth_score);

                nameView.setText(name);
                scoreView.setText("" + score);


                break;



        }


    }


}

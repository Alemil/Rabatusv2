package com.example.alex.rabatusv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    private final String HIGHSCORE_NAMES_KEY = "highscore_names";
    private final String HIGHSCORE_SCORES_KEY = "highscore_scores";

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_activity);
        //temp
        Button temp = (Button) findViewById(R.id.temp_button);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String restoredNames = prefs.getString(HIGHSCORE_NAMES_KEY, null);
        String restoredScores = prefs.getString(HIGHSCORE_SCORES_KEY,null);

        if(restoredNames != null && restoredScores != null){
                nameList = restoredNames.split(" ",10);
                String[] scoreTempList = restoredScores.split(" ",10);
                for(int i = 0 ; i <10 ; i++){
                    scoreList[i] = Integer.parseInt(scoreTempList[i]);
                    updateListView(i,nameList[i],scoreList[i]);
                }
        }

        //setLists();

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = r.nextInt(100);
                makePopup(score);

            }
        });
    }
/*
    public void setLists(){

        scoreList[0] = prefs.getInt("ONE",0);
        scoreList[1] = prefs.getInt("TWO",0);
        scoreList[2] = prefs.getInt("THREE",0);
        scoreList[3] = prefs.getInt("FOUR",0);
        scoreList[4] = prefs.getInt("FIVE",0);
        scoreList[5] = prefs.getInt("SIX",0);
        scoreList[6] = prefs.getInt("SEVEN",0);
        scoreList[7] = prefs.getInt("EIGHT",0);
        scoreList[8] = prefs.getInt("NINE",0);
        scoreList[9] = prefs.getInt("TEN",0);

        nameList[0] = prefs.getString("ONE", "AAAA");
        nameList[1] = prefs.getString("TWO", "AAAA");
        nameList[2] = prefs.getString("THREE", "AAAA");
        nameList[3] = prefs.getString("FOUR", "AAAA");
        nameList[4] = prefs.getString("FIVE", "AAAA");
        nameList[5] = prefs.getString("SIX","AAAA");
        nameList[6] = prefs.getString("SEVEN","AAAA");
        nameList[7] = prefs.getString("EIGHT","AAAA");
        nameList[8] = prefs.getString("NINE","AAAA");
        nameList[9] = prefs.getString("TEN","AAAA");
    }
*/
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
            String scoreString="";
            String nameString ="";
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

                if(i == 0){
                    scoreString += scoreList[i];
                    nameString += nameList[i];
                }else{
                    scoreString += " " + scoreList[i];
                    nameString += " " + nameList[i];
                }
            }

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

            sharedPreferences.edit().putString(HIGHSCORE_NAMES_KEY, nameString).apply();
            sharedPreferences.edit().putString(HIGHSCORE_SCORES_KEY, scoreString).apply();

        }
    }

    public void updateListView(int i, String name, int score){
        SharedPreferences.Editor editor;

        TextView nameView ;
        TextView scoreView;

        switch ( i ){
            case 0:
                nameView = (TextView) findViewById(R.id.first_name);
                scoreView = (TextView) findViewById(R.id.first_score);

                nameView.setText(name);
                scoreView.setText("" + score);
/*
                editor = prefs.edit();
                editor.putString("ONE",name);
                editor.putInt("ONE",score);
                editor.commit();
                */
                break;

            case 1:

                nameView = (TextView) findViewById(R.id.sec_name);
                scoreView = (TextView) findViewById(R.id.sec_score);

                nameView.setText(name);
                scoreView.setText("" + score);
/*
                editor = prefs.edit();
                editor.putString("TWO", name);
                editor.putInt("TWO", score);
                editor.commit();
*/
                break;

            case 2:

                nameView = (TextView) findViewById(R.id.third_name);
                scoreView = (TextView) findViewById(R.id.third_score);

                nameView.setText(name);
                scoreView.setText("" + score);
/*
                editor = prefs.edit();
                editor.putString("THREE", name);
                editor.putInt("THREE", score);
                editor.commit();

*/
                break;

            case 3:

                nameView = (TextView) findViewById(R.id.fourth_name);
                scoreView = (TextView) findViewById(R.id.fourth_score);

                nameView.setText(name);
                scoreView.setText("" + score);
/*
                editor = prefs.edit();
                editor.putString("FOUR", name);
                editor.putInt("FOUR", score);
                editor.commit();
   */
                break;

            case 4:

                nameView = (TextView) findViewById(R.id.fifth_name);
                scoreView = (TextView) findViewById(R.id.fifth_score);

                nameView.setText(name);
                scoreView.setText("" + score);
/*
                editor = prefs.edit();
                editor.putString("FIVE", name);
                editor.putInt("FIVE", score);
                editor.commit();
*/

                break;

            case 5:

                nameView = (TextView) findViewById(R.id.sixth_name);
                scoreView = (TextView) findViewById(R.id.sixth_score);

                nameView.setText(name);
                scoreView.setText("" + score);
/*
                editor = prefs.edit();
                editor.putString("SIX", name);
                editor.putInt("SIX", score);
                editor.commit();
*/
                break;

            case 6:

                nameView = (TextView) findViewById(R.id.seven_name);
                scoreView = (TextView) findViewById(R.id.seven_score);

                nameView.setText(name);
                scoreView.setText("" + score);
/*
                editor = prefs.edit();
                editor.putString("SEVEN", name);
                editor.putInt("SEVEN", score);
                editor.commit();
*/

                break;

            case 7:

                nameView = (TextView) findViewById(R.id.eight_name);
                scoreView = (TextView) findViewById(R.id.eight_score);

                nameView.setText(name);
                scoreView.setText("" + score);
/*
                editor = prefs.edit();
                editor.putString("EIGHT", name);
                editor.putInt("EIGHT", score);
                editor.commit();
*/

                break;

            case 8:

                nameView = (TextView) findViewById(R.id.ninth_name);
                scoreView = (TextView) findViewById(R.id.ninth_score);

                nameView.setText(name);
                scoreView.setText("" + score);
/*
                editor = prefs.edit();
                editor.putString("NINE", name);
                editor.putInt("NINE", score);
                editor.commit();

*/
                break;

            case 9:

                nameView = (TextView) findViewById(R.id.tenth_name);
                scoreView = (TextView) findViewById(R.id.tenth_score);

                nameView.setText(name);
                scoreView.setText("" + score);
/*
                editor = prefs.edit();
                editor.putString("TEN",name);
                editor.putInt("TEN",score);
                editor.commit();
*/

                break;



        }


    }


}

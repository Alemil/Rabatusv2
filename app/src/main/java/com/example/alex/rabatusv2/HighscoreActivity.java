package com.example.alex.rabatusv2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by Mathias Linde on 18-06-2015.
 */
public class HighscoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_activity);

//        TableLayout scoreList = (TableLayout) findViewById(R.id.score_list);
        settingUpLists();
    }

    public  void settingUpLists(){
        //temp

        TableLayout list = (TableLayout) findViewById(R.id.score_list);
        for(int i = 1 ; i <= 10 ; i++){
            


        }
    }
}

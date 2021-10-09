package com.example.threebuttonchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE =
            "com.example.android.threebuttonchallenge.extra.MESSAGE";
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String testo = getString(R.string.article_text);

        /*** Gestione con l'estrazione del testo tramite il metodo SUBSTRING ***/
        //String stringTesto1 = testo.substring(0, 40);

        switch (view.getId()) {
            case R.id.button_one:
                String testo1 = testo.substring(0,nthOccorrenza(testo,"\n",1));
                intent.putExtra(EXTRA_MESSAGE, testo1);
                break;
            case R.id.button_two:
                String testo2 = testo.substring(nthOccorrenza(testo,"\n",2),nthOccorrenza(testo,"\n",3));
                intent.putExtra(EXTRA_MESSAGE, testo2);
                break;
            case R.id.button_three:
                String testo3 = testo.substring(nthOccorrenza(testo,"\n",4),nthOccorrenza(testo,"\n",10));
                intent.putExtra(EXTRA_MESSAGE, testo3);
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }

        startActivity(intent);
    }

    /**
     *
     * @param str1 Stringa su cui ricercare
     * @param str2 Occorrenza di stringa da ricercare
     * @param n numero di occorrenza da ricercare
     * @return L'index della posizione dell'occorrenza n della stringa str2 all'interno della Stringa str1
     */
    public static int nthOccorrenza(String str1, String str2, int n) {

        String tempStr = str1;
        int tempIndex = -1;
        int finalIndex = 0;
        for(int occurrence = 0; occurrence < n ; ++occurrence){
            tempIndex = tempStr.indexOf(str2);
            if(tempIndex==-1){
                finalIndex = 0;
                break;
            }
            tempStr = tempStr.substring(++tempIndex);
            finalIndex+=tempIndex;
        }
        return --finalIndex;
    }
}
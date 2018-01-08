package com.penguinsonabeach.roster_bots;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Random;

/**
 * Created by Phoenix on 1/6/2018.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity Log" ;
    private final int rosterSize = 15;
    private final int nonZeroIndexes = 14;
    private final int salaryCap = 175;
    private final int individualCap = 100;
    private final int nameLength = 7;
    private final int nameChars = 3;
    private int threshold;
    private int extra;
    private Bot[] botsArray = new Bot[rosterSize];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculateThreshold(rosterSize);
        calculateExtra(rosterSize);
        reserveArrayValues(botsArray,rosterSize);
    }


    protected void generateBots(Bot [] botsArray, int salaryCap, int threshold, int extra){

        //variable to show how much free space in points is available outside of the lowest possible arrangement
        this.extra = extra;

        //variable for random generator
        int bound = salaryCap - (threshold - nonZeroIndexes);

        //loop to traverse array in attempt to update values with random
        for(int i= botsArray.length -1; i >= 0; i--){
            Random rand = new Random();
            int temp = rand.nextInt(bound + 1);
            //update bound for next slot
            bound -= i;
            extra -= temp - i;
            for(int j = 0; j < botsArray.length; j++){
                if(bound == botsArray[j].getTas()){
                    i = i-1;

                }
            }
            if(extra < 0){
                return;
            }
            else{
                botsArray[i].setTas(temp);
            }
        }


    }

    /*
    *
    * Reserve the lower values to ensure that we are always below the cap
    *
     */
    protected void reserveArrayValues(Bot [] array, int size){

        for(int i = 0; i<array.length;i++){
            Bot bot = new Bot(i);
            array[i] = bot;
        }

    }

    protected void setBotNames(Bot[] array, int nameLength, int nameChars) {
        Random rand = new Random();
        StringBuilder randomNameBuilder = new StringBuilder();
        char randChar;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < nameChars; j++) {
                randChar = (char) (rand.nextInt(96) + 32);
                randomNameBuilder.append(randChar);
            }
            for (int k = nameChars; k < nameLength; k++) {
                randChar = (char) (rand.nextInt(96) + 32);
                randomNameBuilder.append(randChar);
            }
            
            array[i].setName(randomNameBuilder.toString());
        }
    }

    /*
    *
    *   Method that uses the size of the array to determine what value point it becomes impossible to
    *   fill array with unique values and remain under total cap. This initial value only holds true at the initial index of the array
    *
     */
    protected int calculateThreshold(int size){

        threshold = ((size - 1)*(size))/2;
        Log.d(TAG,"Threshold set to: " + threshold);

        return threshold;
    }

    /*
    *
    *   Method that calculates the difference between total points allowed
    *   and point total if all values were lowest possible
    *
     */
    protected int calculateExtra(int size){

        extra = salaryCap -(((size - 1)*(size))/2);
        Log.d(TAG,"Extra set to : " + extra);
        return extra;
    }
}

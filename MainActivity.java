package com.penguinsonabeach.roster_bots;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Phoenix on 1/8/2018.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity Log" ;
    private final int rosterSize = 15;
    private final int salaryCap = 175;
    private final int nameLength = 7;
    private final int nameChars = 3;
    private int threshold;
    private Bot[] botsArray = new Bot[rosterSize];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        generateBots(rosterSize,calculateThreshold(rosterSize, salaryCap));
        //setBotNames(nameLength,nameChars);
        //printToString();
    }


    protected void generateBots(int salaryCap, int threshold){

        //variable for random generator in this case it equals 84
        int bound = threshold;

        //HashSet used in dupe checking
        Set<Integer> duplist = new HashSet<Integer>();

        //declare random variable
        Random rand = new Random();

        //sum int for troubleshooting
        int sum=0;

        //loop to traverse array in attempt to update values with random
        for(int i= botsArray.length -1; i >= 0; i--){
            //Log.d(TAG,"Index " + i);
            int temp = rand.nextInt(bound);

            //Duplicate check hashset will return true if able to add/ false if element already exists. True = no dupe/ False means dupe.
            if(duplist.add(temp)){

                // add generated value to array of objects
                botsArray[i] = new Bot(temp);
                sum += temp;
                Log.d(TAG," Index : " + i + " Value : " +temp);
                Log.d(TAG," Index : " + i + " sum : " +sum);
                Log.d(TAG, "Index: " + i + " Bound : " + bound);

                // check if generated number is  part of numbers expected within array already
                if( temp >= botsArray.length){

                    bound -=(temp - i);
                }
                if(temp < botsArray.length && i < temp){
                    bound -=(temp - i);
                }
            }
            else {
                i ++;
                //Log.d(TAG,"Outer loop");
                //break;
            }
        }
    }
    


    protected String setBotNames(int nameLength, int nameChars){

        Random rand = new Random();
        char[] username= new char[nameLength];
        String usern;

        //HashSet used in dupe checking
        Set<String> duplist = new HashSet<String>();


            for (int h = 0; h < nameChars; h++) {
                int n = rand.nextInt(26);
                char v = (char) (n + 97);
                username[h] = v;
            }
            for (int i = nameChars; i < nameLength; i++) {
                int n = rand.nextInt(9);
                username[i] = (char) n;
            }
            usern = String.valueOf(username);
            if(!duplist.add(usern)) {
                Log.d(TAG, "Name Generated is " + usern);
                return usern;
            }
            else{
                Log.d(TAG, "Name Generated is " + usern);
                return usern;
            }
        }

    /*
    *
    *   Method that uses the size of the array to determine what value point it becomes impossible to
    *   fill array with unique values and remain under total cap. This initial value only holds true at the initial index of the array
    *
     */
    protected int calculateThreshold(int size, int salaryCap){

        threshold = ((size - 2)*(size - 1))/2;
        Log.d(TAG,"Threshold set to: " + (salaryCap - threshold));

        return salaryCap - threshold;
    }

    protected void printToString(){
        for(int i = 0; i > botsArray.length; i++){
            Log.d(TAG,botsArray[i].toString());
        }
    }


}

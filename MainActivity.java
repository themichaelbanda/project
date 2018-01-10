package com.penguinsonabeach.roster_bots;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Arrays;
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

        setBotsArray(rosterSize);
        generateBots(calculateThreshold(rosterSize, salaryCap));
        setBotNames(nameLength,nameChars);
    }


    protected void generateBots(int threshold){

        //variable for random generator in this case it equals 70
        int bound = threshold;

        //HashSet used in dupe checking
        Set<Integer> duplist = new HashSet<Integer>();

        //declare random variable
        Random rand = new Random();

        //sum int for troubleshooting
        int sum=0;

        //loop to traverse array in attempt to update values with random
        for(int i = botsArray.length - 1; i >= 0; i--){

            int temp = rand.nextInt(bound);

            //While loop using Duplicate check hashset in order to determine if duplicate or not.
            while(!duplist.add(temp + i)) {
                temp = rand.nextInt(bound);
                Log.d(TAG," Duplicate Test Index : " + i +" number gen : " + temp);
            }

            //Lower bound value by the amount generated
            bound -= temp;

            //Loop to make sure that value doesn't cause array to exceed target limit (175 in this case)
            if(bound >= 0) {
                // add generated value to array of objects
                botsArray[i].setTas(i + temp);
                Log.d(TAG,"Value set to : " + botsArray[i].getTas());
            }

            sum += temp;
        }
        Arrays.sort(botsArray);
        for(int i=0; i < botsArray.length;i++){
            //Log.d(TAG,"Index : " + i + " Value " + botsArray[i].getTas() + " Strength: " + botsArray[i].getStrength() + " Agility : " + botsArray[i].getAgility());
        }
    }

    /*
    *   Method to fill array objects with unique names
    *
     */
    protected void setBotNames(int nameLength, int nameChars){

        Random rand = new Random();

        String alpha = "abcdefghijklmnopqrstuvwxyz";

        //HashSet used in dupe checking
        Set<String> duplist = new HashSet<String>();

       for (int j = 0; j < nameLength; j++) {
           StringBuffer randStr = new StringBuffer();
            for (int h = 0; h < nameChars; h++) {
                int n = rand.nextInt(26);
                char v = alpha.charAt(n);
                randStr.append(v);
            }
            for (int i = nameChars; i < nameLength; i++) {
                int n = rand.nextInt(9);
                randStr.append(n);
            }
            if(duplist.add(randStr.toString())){
           Log.d(TAG,"Name : " +randStr.toString());
            botsArray[j].setName(randStr.toString());
            }
            else{j--;}
       }
        //Log.d(TAG,"Name : " +randStr.toString());
            //if(!duplist.add(usern)) {
               // Log.d(TAG, "Name Generated is " + usern);

           // }
           // else{
               // Log.d(TAG, "Name Generated is " + usern);

          //  }
        }

    /*
    *
    *   Method that uses the size of the array to determine what value point it becomes impossible to
    *   fill array with unique values and remain under total cap. This initial value only holds true at the initial index of the array
    *
     */
    protected int calculateThreshold(int size, int salaryCap){

        threshold = ((size - 1)*(size))/2;
        Log.d(TAG,"Threshold set to: " + (salaryCap - threshold));

        return salaryCap - threshold;
    }

    /*
    *   Method to initialize array with default values equal to index
    *   this essentially reserves lower values to ensure we never exceed cap and
    *   always have the smallest unique values available
     */
    protected void setBotsArray(int size){
        Log.d(TAG, "Initializing array");
        for(int i = 0; i < size; i++){

            botsArray[i] = new Bot(i);
            Log.d(TAG, "setBotsArray index " + i+ " Value : " + botsArray[i].getTas());
        }
    }


}

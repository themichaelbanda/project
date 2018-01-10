package com.penguinsonabeach.roster_bots;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Random;


/**
 * Created by Phoenix on 1/8/2018.
 */

public class Bot implements Comparable<Bot>{

    //Variable Declaration

    private int strength, agility, speed,tas;
    private String name;
    final static String TAG = "Bot";


    //Constructors

    public Bot(){}
    public Bot(int tas){

        this.tas = tas;
        setAttributes(tas);

    }

    // Getters and Setters

    public int getStrength(){
        return strength;
    }

    public void setStrength(int strength){
        this.strength = strength;
    }

    public int getAgility(){
        return agility;
    }

    public void setAgility(int agility){
        this.agility = agility;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getTas(){
        return tas;
    }

    public void setTas(int tas){
        this.tas = tas;
        setAttributes(tas);
    }

    public void setAttributes(int tas){

        Random rand = new Random();
        strength = rand.nextInt(tas+1);
        agility = rand.nextInt((tas+1) - strength);
        speed = tas - (strength + agility);
        //Log.d(TAG,"Attributes: " + strength + " , " + agility + " , " + speed);

    }

    public String toString(){
        return " Strength: " + strength + " Agility: " + agility + " Speed: " + speed;
    }

    @Override
    public int compareTo(Bot compareBot) {

        int compareTas = ((Bot) compareBot).getTas();

        return compareTas - this.tas;
    }
}

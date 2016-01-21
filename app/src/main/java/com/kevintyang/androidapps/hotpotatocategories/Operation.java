package com.kevintyang.androidapps.hotpotatocategories;

/**
 * Created by Kevin on 1/19/2016.
 */
public class Operation {

    private int timer = 30;
    private int userSelect = 30;
    private int selectCat = 0;
    private String cat = "Easy!";
    private int selection = 0;
    private int timerSelection = 30;


    public int getTimer(){

        return timer * 1000;
    }

    public int resetTimer(){
        timer = timerSelection;
        return timer;
    }

    public String setCategory(){

        selectCat++;
        switch (selectCat % 5) {
            case 0:
                cat = "Easy!";
                break;
            case 1:
                cat = "Medium!";
                break;
            case 2:
                cat = "Hard!";
                break;
            case 3:
                cat = "Insane!";
                break;
            case 4:
                cat = "Baby!";
                break;
        }
        return cat;
    }

    public void pickTimer(){
        selection++;
        switch (selection % 5) {
            case 0:
                timer = 30;
                break;
            case 1:
                timer = 45;
                break;
            case 2:
                timer = 60;
                break;
            case 3:
                timer = 75;
                break;
            case 4:
                timer = 90;
                break;
        }
        timerSelection = timer;
      }

    public String instructions(){

        return "This game is a fun group game to play with friends or ESL students. \n This game is a hot potato style game where each person \n must say a word in the category on the screen \n before the time runs out! \n Pick your difficult, your time and push start!";
    }


}

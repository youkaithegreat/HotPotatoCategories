package com.kevintyang.androidapps.hotpotatocategories;

import java.util.Random;

/**
 * Created by Kevin on 1/22/2016.
 */
public class Categories {

    Random rand = new Random();

    String[] easy = {"easy1", "easy2","easy3","easy4"};
    String[] medium ={"medium1", "medium2"};
    String[] hard ={"hard1", "hard2"};
    String[] insane = {"insane1", "insane2"};
    String[] baby = {"baby1","baby2"};

    String choice = "empty";
    int selector = 0;

    public Categories(){

    }

    public String getCatString(int category){

        switch (category % 5) {
            case 0:
                choice = easy[rand.nextInt(easy.length)];
                break;
            case 1:
                choice = medium[rand.nextInt(medium.length)];
                break;
            case 2:
                choice = hard[rand.nextInt(hard.length)];
                break;
            case 3:
                choice = insane[rand.nextInt(insane.length)];
                break;
            case 4:
                choice = baby[rand.nextInt(baby.length)];
                break;
        }
        return choice;
    }
}

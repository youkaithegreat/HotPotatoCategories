package com.kevintyang.androidapps.hotpotatocategories;

import java.util.Random;

/**
 * Created by Kevin on 1/22/2016.
 */
public class Categories {

    Random rand = new Random();

    String[] easy = {"things that can fly","colors","numbers","animals","countries","letters","cities","languages","Things in this Room", "Things in the Sky","Things in the Ocean","Words that start with C","Boy Names", "Girl Names", "5 letter words", "Drinks", "Food","Things related to School" };
    String[] medium ={"Animals with four legs","Things that are Hot", "Things that are Expensive","Car Brands", "Clothing Brands","Electronic Brands", "6 Letter Words","Things that are Furry", "Sweet Food", "Spicy Food", "Clothing", "Heavy Things", "Things in Space"};
    String[] hard ={"Car Parts","Poets","Action Movies","8 Letter Words","National Holidays","Words that end with D", "Words with Double Vowels", "Words with only one Vowel", "Two Letter Words", "Things that last a long time", "Intangible Things", "Gems/Jewels", "Spices"};
    //String[] insane = {"insane1", "insane2"};
    //String[] baby = {};

    String choice = "empty";
    int selector = 0;

    public Categories(){

    }

    public String getCatString(int category){

        switch (category % 3) {
            case 0:
                choice = easy[rand.nextInt(easy.length)];
                break;
            case 1:
                choice = medium[rand.nextInt(medium.length)];
                break;
            case 2:
                choice = hard[rand.nextInt(hard.length)];
                break;
        }
        return choice;
    }
}

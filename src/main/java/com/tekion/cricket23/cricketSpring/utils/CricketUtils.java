package com.tekion.cricket23.cricketSpring.utils;

import java.util.Scanner;

public final class CricketUtils {
    private final static Scanner s = new Scanner(System.in);

    public static int getBallOutcome(double probability, double[] HIT_PROBABILITY) {
        if (probability <= HIT_PROBABILITY[0]) {
            return 0;
        } else if (probability <= HIT_PROBABILITY[1]) {
            return 1;
        } else if (probability <= HIT_PROBABILITY[2]) {
            return 2;
        } else if (probability <= HIT_PROBABILITY[3]) {
            return 3;
        } else if (probability <= HIT_PROBABILITY[4]) {
            return 4;
        } else if (probability <= HIT_PROBABILITY[5]) {
            return 6;
        } else {
            return 7;
        }
    }

    public static String getNameInput(){
        return s.next();
    }

    public static int getNumberInput() {
        return s.nextInt();
    }

    public static String printBlankLine(){
        System.out.println(" ");
        return "\n";
    }

    public static String printDottedLine(){
        System.out.println("!----------------------------------------!");
        return "!----------------------------------------!";
    }
}

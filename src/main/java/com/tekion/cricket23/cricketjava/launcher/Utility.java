package com.tekion.cricket23.cricketjava.launcher;

import java.util.Scanner;

public class Utility {
    private final static Scanner s = new Scanner(System.in);

    public static String getNameInput(){
        return s.next();
    }

    public static int getNumberInput() {
        return s.nextInt();
    }

    public static void printBlankLine(){
        System.out.println(" ");
    }

    public static void printDottedLine(){
        System.out.println("!----------------------------------------!");
    }
}
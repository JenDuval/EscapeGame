package com.escapegame.configuration;

import java.util.ArrayList;

public abstract class Memory {
   public static boolean develop;

   public static Integer totalTurn;
   public static Integer numberTurn;

   public static Integer secret;

   public static Integer maximum;
   public static Integer minimum;

   public static Integer maxP;
   public static Integer minP;

   public static Integer numberJ;

   public static ArrayList<Integer> number = new ArrayList<>();

   public static ArrayList<Integer> defNumber = new ArrayList<>();

   public static ArrayList<Integer> max = new ArrayList<>();
   public static ArrayList<Integer> min = new ArrayList<>();

   public static String total = "";
   public static String win = "";
   public static String plus = "+";
   public static String moins = "-";
   public static String egale = "=";
   public static String code = "";

   public static String lang;

   public static String menu = "";
   public static String display1 = "";
   public static String display2 = "";
   public static String display3 = "";
   public static String display4 = "";
   public static String winEnd;
   public static String loseEnd;
   public static String equals;
   public static String turn;
   public static String versus;
   public static String defender;

   public static ArrayList<String> theEnd = new ArrayList<String>();
   public static ArrayList<String> start = new ArrayList<String>();
   public static ArrayList<String> secret1 = new ArrayList<String>();
   public static ArrayList<String> other = new ArrayList<String>();

}

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

   static String lang;
}

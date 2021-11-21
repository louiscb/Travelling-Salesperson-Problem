package com.tsp;

import static com.tsp.TSPAlgorithms.naiveAlgorithm;

import java.util.ArrayList;

public class Main {

   public static boolean IS_VERBOSE = false;

   private static final String VERBOSE_FLAG = "-v";
   private static Kattio io;

   public static void main(String[] args) {
      //      Random rand = new Random();
      //
      //      for (int i = 0; i < 100; i++) {
      //         System.out.println(rand.nextInt(100) + " " + rand.nextInt(100));
      //      }

      initLoggingVerbosity(args);

      ArrayList<City> cities = getCitiesFromSystemIn();

      long startTime = System.nanoTime();
            ArrayList<City> tourOrder = naiveAlgorithm(cities);
//      ArrayList<City> tourOrder = greedyAlgorithm(cities);
      long duration = (System.nanoTime() - startTime);

      if (IS_VERBOSE) {
         System.out.println(duration + " ns");
      }

      tourOrder.forEach(city -> System.out.println(city.getId()));

      io.close();
   }

   private static void initLoggingVerbosity(String[] args) {
      String verboseString = null;

      if (args.length > 0) {
         verboseString = args[0];
      }

      if (verboseString != null && verboseString.equals(VERBOSE_FLAG)) {
         IS_VERBOSE = true;
      }
   }

   private static ArrayList<City> getCitiesFromSystemIn() {
      io = new Kattio(System.in, System.out);
      int n = io.getInt();
      ArrayList<City> cities = new ArrayList<City>();

      for (int i = 0; i < n; i++) {
         double x = io.getDouble();
         double y = io.getDouble();
         City city = new City(i, x, y);
         cities.add(city);
      }

      return cities;
   }
}

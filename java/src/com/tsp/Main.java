package com.tsp;

import static com.tsp.algorithms.AlgorithmsHelper.totalDistance;
import static com.tsp.algorithms.TSPAlgorithms.twoOpt;

import com.tsp.algorithms.TSPAlgorithms;
import com.tsp.domain.City;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Main {

   public static boolean IS_VERBOSE = false;

   private static final String VERBOSE_FLAG = "-v";
   private static Kattio io;

   public static void main(String[] args) throws Exception {
      initLoggingVerbosity(args);
      ArrayList<City> cities = getCitiesFromSystemIn();

      if (IS_VERBOSE) {
         benchmark(cities);
      } else {
         ArrayList<City> tourOrder = twoOpt(cities);
         tourOrder.forEach(city -> System.out.println(city.getId()));
      }

      io.close();
   }

   private static void benchmark(ArrayList<City> cities) throws Exception {
      System.out.println("Benchmarking algorithms with tour size: " + cities.size());

      System.out.println("\n---~ Naive ~---");
      if (cities.size() > 10) {
         System.out.println("Size too large for naive...");
      } else {
         testSingleAlgorithm(() -> TSPAlgorithms.naive(cities));
      }

      System.out.println("\n---~ 2-opt ~---");
      testSingleAlgorithm(() -> twoOpt(cities));

      System.out.println("\n---~ Greedy ~---");
      testSingleAlgorithm(() -> TSPAlgorithms.greedy(cities));
   }

   private static void testSingleAlgorithm(Callable<ArrayList<City>> func) throws Exception {
      int numIterations = 5;
      int bestDistance = Integer.MAX_VALUE;
      double time = 0;

      for (int i = 0; i < numIterations; i++) {
         long startTime = System.nanoTime();
         ArrayList<City> tourOrder = func.call();
         long duration = (System.nanoTime() - startTime);

         double seconds = (double) duration / 1000000000;
         time += seconds;

         int distance = totalDistance(tourOrder);
         if (distance < bestDistance) {
            bestDistance = distance;
         }

         System.out.println(i + 1 + ") Duration: " + seconds + " s. Distance: " + distance);
      }

      System.out.println("--");
      System.out.println("Best distance " + bestDistance);
      System.out.println("Average execution time: " + time / numIterations);
      System.out.println("--");
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

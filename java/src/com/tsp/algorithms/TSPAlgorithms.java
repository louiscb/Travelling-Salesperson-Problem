package com.tsp.algorithms;

import static com.tsp.algorithms.AlgorithmsHelper.createDistanceMatrix;

import com.tsp.domain.City;

import java.util.ArrayList;

public class TSPAlgorithms {

   public static ArrayList<City> naive(ArrayList<City> cities) {
      Naive naiveAlgorithm = new Naive();
      return naiveAlgorithm.findShortestTour(cities);
   }

   public static ArrayList<City> greedy(ArrayList<City> cities) {
      Greedy greedyAlgorithm = new Greedy();
      return greedyAlgorithm.findShortestTour(cities);
   }

   public static ArrayList<City> twoOpt(ArrayList<City> cities) {
      int[][] distanceMatrix = createDistanceMatrix(cities);
      TwoOpt twoOpt = new TwoOpt();
      return twoOpt.findShortestTour(cities, distanceMatrix);
   }

   public static ArrayList<City> twoOpt(ArrayList<City> cities, int[][] distanceMatrix) {
      TwoOpt twoOpt = new TwoOpt();
      return twoOpt.findShortestTour(cities, distanceMatrix);
   }

   //   public static ArrayList<City> algorithm(ArrayList<City> cities) {
   //      Random rand = new Random(System.currentTimeMillis());
   //
   //      int[][] distanceMatrix = createDistanceMatrix(cities);
   //
   //      Cities tour = null;
   //      int bestDistance = Integer.MAX_VALUE;
   //      int iterations = 10;
   //
   //      while (iterations > 0) {
   //         int r = rand.nextInt(Integer.MAX_VALUE);
   //
   //         ArrayList<City> tour = createInitialTour(cities, distanceMatrix);
   //         //         System.out.println("Current tour: " + shortestTour.getDistance());
   //
   //         if (shortestTour.getDistance() < bestDistance) {
   //            tour = shortestTour;
   //            bestDistance = shortestTour.getDistance();
   //         }
   //
   //         iterations--;
   //      }
   //      //      System.out.println("best tour " + bestDistance);
   //
   //      ArrayList<City> newTour = twoOpt(tour.getTour(), distanceMatrix);
   //
   //      return newTour;
   //   }
}

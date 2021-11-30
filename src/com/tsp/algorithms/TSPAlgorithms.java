package com.tsp.algorithms;

import com.tsp.City;

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
      TwoOpt twoOpt = new TwoOpt();
      return twoOpt.findShortestTour(cities);
   }

   public static ArrayList<City> branchAndBound(ArrayList<City> cities) {
      BranchAndBound branchAndBound = new BranchAndBound();
      return branchAndBound.findShortestTour(cities);
   }
}

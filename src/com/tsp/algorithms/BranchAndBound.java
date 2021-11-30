package com.tsp.algorithms;

import com.tsp.City;

import java.util.ArrayList;

public class BranchAndBound implements TSPAlgorithm {

   @Override
   public ArrayList<City> findShortestTour(ArrayList<City> cities) {
      int[][] matrix = createDistanceMatrix(cities);

      ArrayList<City> finalTour = new ArrayList<>();

      finalTour.add(new City(1, 2, 4));
      return finalTour;
   }

   private int[][] createDistanceMatrix(ArrayList<City> cities) {
      int[][] matrix = new int[cities.size()][cities.size()];
      for (int i = 0; i < cities.size(); i++) {
         for (int j = 0; j < cities.size(); j++) {
            int distance;
            if (i == j) {
               distance = 0;
            } else {
               distance = cities.get(i).distanceTo(cities.get(j));
            }

            matrix[i][j] = distance;
         }
      }

      return matrix;
   }
}
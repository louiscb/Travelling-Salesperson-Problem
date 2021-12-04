package com.tsp.algorithms;

import static com.tsp.algorithms.AlgorithmsHelper.totalDistance;

import com.tsp.domain.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SavingsMethod {

   public static ArrayList<City> createInitialTour(ArrayList<City> cities, int[][] distances) {
      ArrayList<City> tour = new ArrayList<>(cities);
      Random rand = new Random(System.currentTimeMillis());
      int currentDistance = totalDistance(tour);
      for (int i = 0; i < 10; i++) {
         int r = rand.nextInt(Integer.MAX_VALUE);
         ArrayList<City> newTour = savingsMethod(tour, distances, r % cities.size());

         int newDistance = totalDistance(newTour);
         if (newDistance < currentDistance) {
            currentDistance = newDistance;
            tour = newTour;
         }
      }
      return tour;
   }

   private static ArrayList<City> savingsMethod(ArrayList<City> tour, int[][] distances, int start) {
      int n = tour.size();
      int[][] newMatrix = new int[tour.size()][tour.size()];

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            int distance;
            if (i == j) {
               distance = 0;
            } else {
               distance = distances[i][j] - distances[i][start] - distances[j][start];
            }

            newMatrix[i][j] = distance;
            newMatrix[j][i] = distance;
         }
      }

      return findShortestTour(newMatrix, start, tour);
   }

   public static ArrayList<City> findShortestTour(int[][] distances, int start, ArrayList<City> cities) {
      ArrayList<City> tour = new ArrayList<>();
      HashMap<Integer, Boolean> visitedCities = new HashMap<>();
      visitedCities.put(start, true);
      tour.add(cities.get(start));

      int n = cities.size();
      int previousIndex = start;
      for (int i = 0; i < n; i++) {
         if (i == start) {
            continue;
         }
         int dist = Integer.MAX_VALUE;

         City city = null;

         for (int j = 0; j < n; j++) {
            if (visitedCities.containsKey(j)) {
               continue;
            }

            int currentDist = distances[previousIndex][j];

            if (currentDist < dist) {
               dist = currentDist;
               city = cities.get(j);
               previousIndex = j;
            }
         }

         visitedCities.put(previousIndex, true);
         tour.add(city);
      }
      return tour;
   }
}

package com.tsp.algorithms;

import com.tsp.City;

import java.util.ArrayList;
import java.util.Map;

/**
 * Implements a naive AKA brute force algorithm.
 * --
 * Calculate all possible routes and choose the one with the shortest distance.
 * Number of routes is: (n-1)!
 */
public class Naive implements TSPAlgorithm {

   @Override
   public ArrayList<City> findShortestTour(ArrayList<City> cities) {
      City firstCity = cities.get(0);

      ArrayList<City> initialPath = new ArrayList<>();
      initialPath.add(firstCity);

      Map.Entry<Integer, ArrayList<City>> shortestTour = findShortestTourBruteForce(
         firstCity,
         cities,
         initialPath,
         0,
         new ArrayList<>(),
         Integer.MAX_VALUE
      );

      return shortestTour.getValue();
   }

   private Map.Entry<Integer, ArrayList<City>> findShortestTourBruteForce(
      City currentCity,
      ArrayList<City> cities,
      ArrayList<City> currentPath,
      int currentDistance,
      ArrayList<City> bestPath,
      int bestDistance
   ) {
      if (currentPath.size() == cities.size()) {
         int distanceBackToStart = currentCity.distanceTo(cities.get(0));
         currentDistance += distanceBackToStart;

         return currentDistance < bestDistance
            ? Map.entry(currentDistance, currentPath)
            : Map.entry(bestDistance, bestPath);
      }

      ArrayList<City> remainingCities = new ArrayList<>(cities);
      remainingCities.removeAll(currentPath);

      for (City city : remainingCities) {
         int distanceBetweenCurrentAndLastCity = currentCity.distanceTo(city);
         ArrayList<City> updatedCurrentPath = new ArrayList<>(currentPath);
         updatedCurrentPath.add(city);

         Map.Entry<Integer, ArrayList<City>> shortestTour = findShortestTourBruteForce(
            city,
            cities,
            updatedCurrentPath,
            currentDistance + distanceBetweenCurrentAndLastCity,
            bestPath,
            bestDistance);

         if (shortestTour.getKey() < bestDistance) {
            bestPath = shortestTour.getValue();
            bestDistance = shortestTour.getKey();
         }
      }

      return Map.entry(bestDistance, bestPath);
   }
}

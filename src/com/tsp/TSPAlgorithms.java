package com.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class TSPAlgorithms {

   /**
    * Implements a naive AKA brute force algorithm.
    * --
    * Calculate all possible routes and choose the one with the shortest distance.
    * Number of routes is: (n-1)!
    */
   public static ArrayList<City> naiveAlgorithm(ArrayList<City> cities) {
      City firstCity = cities.get(0);

      ArrayList<City> initialPath = new ArrayList<>();
      initialPath.add(firstCity);

      ArrayList<City> remainingCities = new ArrayList<>(cities);
      remainingCities.remove(firstCity);

      Map.Entry<Double, ArrayList<City>> shortestTour = findShortestTourBruteForce(
         firstCity,
         cities,
         remainingCities,
         initialPath,
         0,
         new ArrayList<>(),
         Double.MAX_VALUE
      );

      return shortestTour.getValue();
   }

   /**
    * Implements a greedy algorithm.
    * --
    * Greedy is an algorithmic paradigm that builds up a solution piece by piece,
    * always choosing the next piece that offers the most obvious and immediate benefit.
    */
   public static ArrayList<City> greedyAlgorithm(ArrayList<City> cities) {
      ArrayList<City> tour = new ArrayList<>();

      Collections.shuffle(cities);

      City nextCity = cities.get(0);

      while (cities.size() > 0) {
         tour.add(nextCity);
         cities.remove(nextCity);
         double bestDistance = Double.MAX_VALUE;

         for (City city : cities) {
            double distance = calculateDistance(nextCity, city);
            if (distance < bestDistance) {
               nextCity = city;
            }
         }
      }

      return tour;
   }

   public static ArrayList<City> dynamicProgrammingAlgorithm() {
      return null;
   }

   private static Map.Entry<Double, ArrayList<City>> findShortestTourBruteForce(
      City currentCity,
      ArrayList<City> cities,
      ArrayList<City> remainingCities,
      ArrayList<City> currentPath,
      double currentDistance,
      ArrayList<City> bestPath,
      double bestDistance
   ) {
      if (remainingCities.size() == 0) {
         double distanceBackToStart = calculateDistance(currentCity, cities.get(0));
         currentDistance += distanceBackToStart;

         return currentDistance < bestDistance
            ? Map.entry(currentDistance, currentPath)
            : Map.entry(bestDistance, bestPath);
      }

      for (City city : remainingCities) {
         double distanceBetweenCurrentAndLastCity = calculateDistance(currentCity, city);
         ArrayList<City> updatedCurrentPath = new ArrayList<>(currentPath);
         updatedCurrentPath.add(city);
         ArrayList<City> updatedRemainingCities = new ArrayList<>(remainingCities);
         updatedRemainingCities.remove(city);

         Map.Entry<Double, ArrayList<City>> shortestTour = findShortestTourBruteForce(
            city,
            cities,
            updatedRemainingCities,
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

   private static double calculateDistance(City lastCity, City currentCity) {
      Coordinate a = currentCity.getCoordinate();
      Coordinate b = lastCity.getCoordinate();

      // Calculate hypotenuse / euclidean distance
      return Math.sqrt((b.getY() - a.getY()) * (b.getY() - a.getY()) + (b.getX() - a.getX()) * (b.getX() - a.getX()));
   }
}

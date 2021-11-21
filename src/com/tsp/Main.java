package com.tsp;

import java.util.ArrayList;
import java.util.Map;

public class Main {

   private static final String VERBOSE_FLAG = "-v";
   private static boolean verbose = false;

   static int n = 0;

   public static void main(String[] args) {
      String verboseString = null;

      if (args.length > 0) {
         verboseString = args[0];
      }

      if (verboseString != null && verboseString.equals(VERBOSE_FLAG)) {
         verbose = true;
      }

      Kattio io = new Kattio(System.in, System.out);
      int n = io.getInt();
      ArrayList<City> cities = new ArrayList<City>();

      for (int i = 0; i < n; i++) {
         double x = io.getDouble();
         double y = io.getDouble();
         City city = new City(i, x, y);
         cities.add(city);
      }

      long startTime = System.nanoTime();
//      ArrayList<City> tourOrder = TSPAlgorithm(cities);
      ArrayList<City> tourOrder = naiveAlgorithm(cities);
      long duration = (System.nanoTime() - startTime);

      if (verbose) {
         System.out.println(duration + " ns");
      }

      tourOrder.forEach(city -> System.out.println(city.getId()));

      io.close();
   }

   /**
    * This is where we implement the algorithm to solve TSP
    *
    * @return list of ids of cities in ascending order to visit
    */
   private static ArrayList<City> TSPAlgorithm(ArrayList<City> cities) {
      return cities;
   }

   /**
    *
    * Implement naive AKA brute force algorithm.
    *
    * Calculate all possible routes and choose the one with the shortest distance.
    *
    * Number of routes is: (n-1)!
    *
    * @param cities
    * @return
    */
   private static ArrayList<City> naiveAlgorithm(ArrayList<City> cities) {
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

      System.out.println("total n: " + n);

      return shortestTour.getValue();
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

         n++;

         return currentDistance < bestDistance
            ? Map.entry(currentDistance, currentPath)
            : Map.entry(bestDistance, bestPath);
      }

    //  System.out.print("Current city: " + currentCity.getId() + "Remaining cities: ");
    //  remainingCities.forEach(city -> System.out.print(city.getId()));
   //   System.out.println("...");

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
      return Math.sqrt((b.getY() - a.getY()) * (b.getY()- a.getY()) + (b.getX() - a.getX()) * (b.getX() - a.getX()));
   }
}

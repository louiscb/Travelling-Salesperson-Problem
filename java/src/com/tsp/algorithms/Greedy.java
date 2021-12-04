package com.tsp.algorithms;

import com.tsp.domain.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Implements a greedy algorithm.
 * --
 * Greedy is an algorithmic paradigm that builds up a solution piece by piece,
 * always choosing the next piece that offers the most obvious and immediate benefit.
 */
public class Greedy implements TSPAlgorithm {

   @Override
   public ArrayList<City> findShortestTour(ArrayList<City> cities) {
      ArrayList<City> startingCities = new ArrayList<>(cities);
      ArrayList<City> tour = new ArrayList<>();

      Collections.shuffle(startingCities);

      City nextCity = startingCities.get(0);

      while (startingCities.size() > 0) {
         tour.add(nextCity);
         startingCities.remove(nextCity);
         double bestDistance = Double.MAX_VALUE;

         for (City city : startingCities) {
            int distance = nextCity.distanceTo(city);
            if (distance < bestDistance) {
               nextCity = city;
            }
         }
      }

      return tour;
   }
}

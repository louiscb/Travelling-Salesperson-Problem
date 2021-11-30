package com.tsp.algorithms;

import com.tsp.City;

import java.util.ArrayList;

public class AlgorithmsHelper {

   public static int totalDistance(ArrayList<City> cities) {
      int totalDistance = 0;

      for (int i = 0; i < cities.size() - 1; i++) {
         totalDistance += cities.get(i).distanceTo(cities.get(i + 1));
      }
      return totalDistance + cities.get(cities.size() - 1).distanceTo(cities.get(0));
   }
}

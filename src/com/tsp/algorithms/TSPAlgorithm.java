package com.tsp.algorithms;

import com.tsp.City;

import java.util.ArrayList;

public interface TSPAlgorithm {

   public ArrayList<City> findShortestTour(ArrayList<City> cities);
}

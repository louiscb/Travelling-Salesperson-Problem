package com.tsp.algorithms;

import com.tsp.domain.City;

import java.util.ArrayList;

public interface TSPAlgorithm {

   public ArrayList<City> findShortestTour(ArrayList<City> cities);
}

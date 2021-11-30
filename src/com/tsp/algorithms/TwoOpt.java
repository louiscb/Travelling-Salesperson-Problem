package com.tsp.algorithms;

import static com.tsp.algorithms.TSPAlgorithms.greedy;

import com.tsp.City;

import java.util.ArrayList;
import java.util.Collections;

public class TwoOpt implements TSPAlgorithm {

   public static final int ITERATIONS = 25;

   public ArrayList<City> findShortestTour(ArrayList<City> cities) {
      ArrayList<City> tour = greedy(cities);

      int iterations = ITERATIONS;

      do {
         iterations--;

         for (int i = 0; i < tour.size() - 1; i++) {
            for (int j = i + 1; j < tour.size(); j++) {
               int pointerBeforeI = i - 1 < 0 ? tour.size() - 1 : i - 1;
               int pointerAfterJ = j + 1 >= tour.size() ? 0 : j + 1;

               int distanceCurrentPair =
                  tour.get(i).distanceTo(tour.get(pointerBeforeI)) + tour.get(j).distanceTo(tour.get(pointerAfterJ));
               //                     + tour.get(i).distanceTo(tour.get(i + 1)) + tour.get(j).distanceTo(tour.get(j - 1));
               int distanceSwapPair =
                  tour.get(i).distanceTo(tour.get(pointerAfterJ)) + tour.get(j).distanceTo(tour.get(pointerBeforeI));
               //               + tour.get(j).distanceTo(tour.get(i + 1)) + tour.get(i).distanceTo(tour.get(j - 1));

               if (distanceSwapPair < distanceCurrentPair) {
                  Collections.swap(tour, i, j);
               }
            }
         }
      } while ((iterations != 0));

      return tour;
   }
}

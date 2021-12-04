package com.tsp.algorithms;

import com.tsp.domain.City;
import com.tsp.domain.Edge;

import java.util.ArrayList;
import java.util.Random;

public class TwoOpt {

   Random rand;

   public ArrayList<City> findShortestTour(ArrayList<City> cities, int[][] distanceMatrix) {
      rand = new Random(System.currentTimeMillis());
      ArrayList<City> tour = new ArrayList<>(cities);
      int n = tour.size();

      int firstIteration = 100;
      while (firstIteration > 0) {
         for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               swapIfBeneficial(distanceMatrix, tour, i, j);
            }
         }
         firstIteration--;
      }

      int iterations = 1000000;

      while (iterations > 0) {
         swapIfBeneficial(distanceMatrix, tour, rand.nextInt(n), rand.nextInt(n));
         iterations--;
      }
      return tour;
   }

   private void swapIfBeneficial(int[][] distanceMatrix, ArrayList<City> tour, int i, int j) {
      int n = tour.size();
      if (i == j) {
         return;
      }

      int nextI = (i + 1) % n;
      int nextJ = (j + 1) % n;

      Edge firstEdge = new Edge(tour.get(i).getId(), tour.get(nextI).getId());
      Edge secondEdge = new Edge(tour.get(j).getId(), tour.get(nextJ).getId());

      if (shouldSwap(firstEdge, secondEdge, distanceMatrix)) {
         swap(i, j, tour);
      }
   }

   private void swap(int i, int j, ArrayList<City> tour) {
      int n = tour.size();
      int firstI = i;

      i = (i + 1) % n;

      int swaps = j - i;
      if (swaps < 0) {
         swaps += n;
      }

      if (swaps > n / 2) {
         swap(j, firstI, tour);
         return;
      }

      for (int swap = 0; swap < swaps / 2; swap++) {
         City temp = tour.get(i);
         tour.set(i, tour.get(j));
         tour.set(j, temp);

         i = (i + 1) % n;
         j = (n + j - 1) % n;
      }
   }

   private boolean shouldSwap(Edge firstEdge, Edge secondEdge, int[][] distanceMatrix) {
      int currentLength = distanceMatrix[firstEdge.getA()][firstEdge.getB()]
         + distanceMatrix[secondEdge.getA()][secondEdge.getB()];

      int newLength = distanceMatrix[firstEdge.getA()][secondEdge.getA()]
         + distanceMatrix[firstEdge.getB()][secondEdge.getB()];

      return currentLength > newLength;
   }
}

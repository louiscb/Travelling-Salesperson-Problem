package com.tsp.algorithms;

import static com.tsp.algorithms.AlgorithmsHelper.totalDistance;
import static com.tsp.algorithms.TSPAlgorithms.greedy;

import com.tsp.City;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class TwoOpt implements TSPAlgorithm {

    public static final int ITERATIONS = 100;

    Random random;

    public ArrayList<City> findShortestTour(ArrayList<City> cities) {
        ArrayList<City> tour = greedy(cities);
        Collections.shuffle(tour);
        random = new Random();
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
        iterations = 1000;
        do {
            iterations--;

            for (int i = 0; i < tour.size() - 1; i++) {
                int j = getRandomIndex(cities.size());
                //for (int j = i + 1; j < tour.size(); j++) {
                int distanceCurrentPair = getCurrentDistances(i, j, tour);
                int distanceSwapPair = getSwappedDistances(i, j, tour);

                if (distanceSwapPair < distanceCurrentPair) {
                    Collections.swap(tour, i, j);
                }
                //  }
            }

        } while ((iterations != 0));

        return tour;
    }

    private int getRandomIndex(int size) {
        return random.nextInt(size - 1) + 1;
    }

    private int getSwappedDistances(int i, int j, ArrayList<City> tour) {
        int pointerBeforeI = i - 1 < 0 ? tour.size() - 1 : i - 1;
        int pointerAfterJ = j + 1 >= tour.size() ? 0 : j + 1;

        if (i == j - 1) {
            return tour.get(i).distanceTo(tour.get(pointerAfterJ)) + tour.get(j).distanceTo(tour.get(pointerBeforeI))
                    + (2 * (tour.get(i).distanceTo(tour.get(j))));
        } else if (i == pointerAfterJ) {
            return tour.get(i).distanceTo(tour.get(j - 1)) + tour.get(j).distanceTo(tour.get(i + 1))
                    + (2 * (tour.get(i).distanceTo(tour.get(j))));
        } else {
            return tour.get(i).distanceTo(tour.get(pointerAfterJ)) + tour.get(i).distanceTo(tour.get(j - 1))
                    + tour.get(j).distanceTo(tour.get(pointerBeforeI)) + tour.get(j).distanceTo(tour.get(i + 1));
        }
    }

    private int getCurrentDistances(int i, int j, ArrayList<City> tour) {
        int pointerBeforeI = i - 1 < 0 ? tour.size() - 1 : i - 1;
        int pointerAfterJ = j + 1 >= tour.size() ? 0 : j + 1;

        return tour.get(i).distanceTo(tour.get(pointerBeforeI)) + tour.get(i).distanceTo(tour.get(i + 1))
                + tour.get(j).distanceTo(tour.get(j - 1)) + tour.get(j).distanceTo(tour.get(pointerAfterJ));
    }
}

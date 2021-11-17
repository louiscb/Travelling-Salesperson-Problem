package com.tsp;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        ArrayList<City> cities = new ArrayList<City>();

        for (int i = 0; i < n; i++) {
            double x = io.getDouble();
            double y = io.getDouble();
            City city = new City(i, x, y);
            cities.add(city);
        }

        ArrayList<Integer> travelOrder = TSPAlgorithm(cities);

        travelOrder.forEach(io::println);

        io.close();
    }

    /**
     * This is where we implement the algorithm to solve TSP
     *
     * @param cities
     * @return list of ids of cities in ascending order to visit
     */
    private static ArrayList<Integer> TSPAlgorithm(ArrayList<City> cities) {
        ArrayList<Integer> travelOrder = new ArrayList<Integer>();

        for (City city : cities) {
            travelOrder.add(city.getId());
        }

        return travelOrder;
    }
}

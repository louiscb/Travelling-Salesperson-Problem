package com.tsp;

public class City {
    private int id;
    private Coordinate coordinate;

    public City(int id, double xCoordinate, double yCoordinate) {
        this.id = id;
        this.coordinate = new Coordinate(xCoordinate, yCoordinate);
    }

    public int getId() {
        return id;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}

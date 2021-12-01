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

   public int distanceTo(City nextCity) {
      Coordinate a = this.getCoordinate();
      Coordinate b = nextCity.getCoordinate();

      // Calculate hypotenuse / euclidean distance
      double sqrt = Math.sqrt((b.getY() - a.getY()) * (b.getY() - a.getY())
              + (b.getX() - a.getX()) * (b.getX() - a.getX()));
      return (int) Math.rint(sqrt);
   }
}

package test;

import com.tsp.City;
import com.tsp.algorithms.BranchAndBound;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BranchAndBoundTest {

   @Test
   void testSingleCity() {
      BranchAndBound branchAndBound = new BranchAndBound();

      ArrayList<City> cities = new ArrayList<>();
      cities.add(new City(1, 0, 0));
      cities.add(new City(2, 0, 5));
      cities.add(new City(3, 5, 5));
      cities.add(new City(4, 5, 0));
      cities.add(new City(5, 25, 25));
      cities.add(new City(6, 30, 25));
      cities.add(new City(7, 3, 6));
      cities.add(new City(8, 11, 2));
      cities.add(new City(9, 30, 0));
      cities.add(new City(10, 10, 10));

      ArrayList<City> shortestTour = branchAndBound.findShortestTour(cities);

      //      assertEquals(cities.get(0).getId(), shortestTour.get(0).getId());
   }
}

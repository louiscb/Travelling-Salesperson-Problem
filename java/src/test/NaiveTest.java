package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tsp.domain.City;
import com.tsp.algorithms.Naive;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class NaiveTest {

   @Test
   void testSingleCity() {
      Naive naive = new Naive();

      City city = new City(1, 5, 5);
      ArrayList<City> cities = new ArrayList<>();
      cities.add(city);

      ArrayList<City> shortestTour = naive.findShortestTour(cities);

      assertEquals(cities.get(0).getId(), shortestTour.get(0).getId());
   }

   @Test
   void testFourCities() {
      Naive naive = new Naive();

      ArrayList<City> cities = new ArrayList<>();
      cities.add(new City(1, 0, 0));
      cities.add(new City(2, 0, 5));
      cities.add(new City(3, 5, 5));
      cities.add(new City(4, 5, 0));

      ArrayList<City> shortestTour = naive.findShortestTour(cities);

      assertEquals(1, shortestTour.get(0).getId());
      assertEquals(2, shortestTour.get(1).getId());
      assertEquals(3, shortestTour.get(2).getId());
      assertEquals(4, shortestTour.get(3).getId());
   }
}
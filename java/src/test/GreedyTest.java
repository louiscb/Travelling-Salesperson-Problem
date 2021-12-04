package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tsp.domain.City;
import com.tsp.algorithms.Greedy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GreedyTest {

   @Test
   void testSingleCity() {
      Greedy greedy = new Greedy();

      City city = new City(1, 5, 5);
      ArrayList<City> cities = new ArrayList<>();
      cities.add(city);

      ArrayList<City> shortestTour = greedy.findShortestTour(cities);

      assertEquals(cities.get(0).getId(), shortestTour.get(0).getId());
   }
}
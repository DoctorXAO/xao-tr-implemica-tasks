package Task2;

import java.util.*;

public class Task2Test {
    public static void main(String[] args) {
        new Task2Test().start(); // start Task 2
    }

    private void start() {
        try {
            List<City> cities = new ArrayList<>();

            City a = new City();
            a.setId(1);
            a.setName("a");
            a.setNumberOfNeighbors(4);

            HashMap<Integer, Integer> directionPriceA = new HashMap<>();
            directionPriceA.put(4, 2);
            directionPriceA.put(5, 10);
            directionPriceA.put(2, 1);
            directionPriceA.put(3, 7);

            a.setDirectionPrice(directionPriceA);

            City b = new City();
            b.setId(2);
            b.setName("b");
            b.setNumberOfNeighbors(2);

            HashMap<Integer, Integer> directionPriceB = new HashMap<>();
            directionPriceB.put(1, 1);
            directionPriceB.put(3, 1);

            b.setDirectionPrice(directionPriceB);

            City c = new City();
            c.setId(3);
            c.setName("c");
            c.setNumberOfNeighbors(3);

            HashMap<Integer, Integer> directionPriceC = new HashMap<>();
            directionPriceC.put(1, 1);
            directionPriceC.put(4, 1);
            directionPriceC.put(2, 10);

            c.setDirectionPrice(directionPriceC);

            City d = new City();
            d.setId(4);
            d.setName("d");
            d.setNumberOfNeighbors(4);

            HashMap<Integer, Integer> directionPriceD = new HashMap<>();
            directionPriceD.put(3, 6);
            directionPriceD.put(5, 1);
            directionPriceD.put(1, 2);
            directionPriceD.put(2, 3);

            d.setDirectionPrice(directionPriceD);

            City e = new City();
            e.setId(5);
            e.setName("e");
            e.setNumberOfNeighbors(4);

            HashMap<Integer, Integer> directionPriceE = new HashMap<>();
            directionPriceE.put(1, 1);
            directionPriceE.put(2, 1);
            directionPriceE.put(3, 1);
            directionPriceE.put(4, 1);

            e.setDirectionPrice(directionPriceE);

            cities.add(a);
            cities.add(b);
            cities.add(c);
            cities.add(d);
            cities.add(e);

            System.out.println(recursivePathfinding(
                    cities,
                    a,
                    e,
                    new ArrayList<>(List.of(a.getId())),
                    0,
                    new ArrayList<>()));

            System.out.println(recursivePathfinding(
                    cities,
                    a,
                    b,
                    new ArrayList<>(List.of(a.getId())),
                    0,
                    new ArrayList<>()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String recursivePathfinding(List<City> cities,
                                      City source,
                                      City destination,
                                      List<Integer> visited,
                                      int total,
                                      List<Integer> costPaths) {
        HashMap<Integer, Integer> directionPrice = source.getDirectionPrice();
        boolean isVisited = false;

        for (int k : directionPrice.keySet()) {
            if (k == destination.getId())
                costPaths.add(total + directionPrice.get(k));
            else {
                for (int v : visited)
                    if (k == v) {
                        isVisited = true;
                        break;
                    }

                if (isVisited) {
                    isVisited = false;
                    continue;
                }

                visited.add(k);
                total += directionPrice.get(k);
                Optional<City> newCourse = cities.stream().filter(c -> c.getId() == k).findFirst();

                try {
                    if (newCourse.isPresent()) {
                        recursivePathfinding(
                                cities,
                                newCourse.get(),
                                destination,
                                visited,
                                total,
                                costPaths);

                        total -= directionPrice.get(k);
                    }
                    else
                        throw new Exception(String.format("\n[ERROR] Can't find the city with %s id", k));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (costPaths.isEmpty())
            return "";
        else
            return Collections.min(costPaths).toString();
    }
}

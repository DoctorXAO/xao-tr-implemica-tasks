package Task2;

import java.util.*;

public class Task2 {
    private final List<City> cities = new ArrayList<>(); // init of a list for cities

    private int n = 0; // init of a var for count of cities

    public static void main(String[] args) {
        new Task2().start(); // start Task 2
    }

    private void start() {
        n = enterNumberOfCities(); // the number of cities (<= 10000)

        for (int i = 0; i < n; i++) // input all cities
            cities.add(enterParametersOfCity(n,i + 1)); // add new city to the list of cities

        int r = enterNumberOfPaths(); // init of a var for number of paths between cities

        List<SourceDestination> sourceDestinationList = new ArrayList<>(); // init of a list for source-destination

        // input source-destination for finding the cheapest path between them
        for (int i = 0; i < r; i++)
            sourceDestinationList.add(enterSourceDestination(cities)); // add new source-destination to the list

        findTheCheapestPaths(cities, sourceDestinationList); // find the cheapest paths
    }

    private int enterNumberOfCities() {
        int i; // number of cities

        while(true) {
            try {
                System.out.println("Enter the value of the number of cities.");
                System.out.println("Value must be from 1 to 10000.");
                System.out.print("n = ");

                i = inputInt(); // enter a number of cities

                if (i >= 1 && i <= 10000) // the number of cities must be from 1 to 10000
                    break; // stop a loop
                else
                    throw new Exception("\n[ERROR] Incorrect value! Value must be from 1 to 10000. Try again.\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return i;
    }

    private City enterParametersOfCity(int total, int id) {
        City city = new City(); // init of a new city

        city.setId(id); // set ID to the city

        // Enter city name
        while(true) {
            try {
                System.out.println("Enter the name of the city, use only up to 10 letters (A-Z, a-z).");
                System.out.printf("name (id: %s) = ", id);

                String data = new Scanner(System.in).nextLine(); // input a name of the city

                // check whether the name of the city is present in the list of cities
                for(City c : cities)
                    if (c.getName().equals(data))
                        throw new Exception("\n[ERROR] The name of the city is present. Try an another name.\n");

                city.setName(data); // set the name to the city

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Enter the number of the neighbors
        while(true) {
            try {
                System.out.printf("Enter the number of the neighbors (greater than 0 and less than %s).\n", total);
                System.out.print("numberOfNeighbors = ");

                int numberOfNeighbors = inputInt(); // enter a number of neighbors

                if (numberOfNeighbors >= total) // if the number of neighbor is bigger than the total of cities
                    throw new Exception(
                            "\n[ERROR] The number of neighbors is greater than total cities. Use a less number.\n");

                city.setNumberOfNeighbors(numberOfNeighbors); // set the number of neighbors to the city

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // enter source-destination
        HashMap<Integer, Integer> sourceDestination = new HashMap<>(); // init a hashmap for source-destination

        for (int i = 0; i < city.getNumberOfNeighbors(); i++) { // enter all neighbors of the city
            while (true) {
                try {
                    System.out.println("Enter direction-price, use the id of the city and a price of the trip [id price].");
                    System.out.printf("[id price] (current city id: %s) = ", id);

                    String[] data = new Scanner(System.in).nextLine().split(" ", 2); // [direction price]

                    if (Integer.parseInt(data[0]) > n)
                        throw new Exception("\n[ERROR] The id of the city is bigger than total cities. Try a less id.\n");
                    else if (!data[0].matches("[0-9]+"))
                        throw new Exception("\n[ERROR] Incorrect direction! Input only an id. Try again.\n");
                    else if (!data[1].matches("[0-9]+"))
                        throw new Exception("\n[ERROR] Incorrect price! Input only a number. Try again.\n");

                    int direction = Integer.parseInt(data[0]); // init a var for direction
                    int price = Integer.parseInt(data[1]); // init a var for price

                    if (direction == id)
                        throw new Exception("\n[ERROR] Incorrect data! Use an another id except the current city id.\n");
                    else if (sourceDestination.containsKey(direction))
                        throw new Exception("\n[ERROR] The id of the direction is already entered. Try an another id.\n");
                    else if (direction < 1)
                        throw new Exception("\n[ERROR] Enter an id of the direction greater than 0.\n");
                    else if (price <= 0)
                        throw new Exception("\n[ERROR] Enter a price of the direction greater than 0.\n");

                    sourceDestination.put(direction, price);

                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        city.setDirectionPrice(sourceDestination);

        return city;
    }

    private int enterNumberOfPaths() {
        int i; // number of the paths between cities

        while (true) {
            try {
                System.out.println("Use a number greater than 0 and less than or equals to 100.");
                System.out.print("Enter the number of the paths: ");

                i = new Scanner(System.in).nextInt(); // input the number of the paths

                if (i <= 0 || i > 100)
                    throw new Exception(
                            "Incorrect data! Use a number greater than 0 and less than or equals to 100. Try again.");

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return i;
    }

    private SourceDestination enterSourceDestination(List<City> cities) {
        SourceDestination sourceDestination = new SourceDestination(); // new source-destination

        while(true) {
            try {
                System.out.println(
                        "Enter source-destination [source destination] to find the cheapest path between cities.");
                System.out.print("[source destination] = ");

                String[] data = new Scanner(System.in).nextLine().split(" ", 2); // [source destination]

                if (data[0].equals(data[1]))
                    throw new Exception("Incorrect data! The cities have the same names. Try again.");

                // find the source from the list of cities
                Optional<City> source = cities.stream().filter(c -> c.getName().equals(data[0])).findFirst();
                // find the destination from the list of cities
                Optional<City> destination = cities.stream().filter(c -> c.getName().equals(data[1])).findFirst();

                if (source.isEmpty())
                    throw new Exception("Unknown source city name. Try again.");
                else if (destination.isEmpty())
                    throw new Exception("Unknown destination city name. Try again.");

                sourceDestination.setSource(source.get()); // set the source to the source-destination
                sourceDestination.setDestination(destination.get()); // set the destination to the source-destination

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return sourceDestination;
    }

    private void findTheCheapestPaths(List<City> cities, List<SourceDestination> sourceDestinationList) {
        // iterate through to find the cheapest paths of source-destination
        for(SourceDestination sourceDestination : sourceDestinationList) {
            City source = sourceDestination.getSource(); // init a var for source
            City destination = sourceDestination.getDestination(); // init a var for destination

            System.out.println(recursivePathfinding(
                    cities,
                    source,
                    destination,
                    new ArrayList<>(List.of(source.getId())),
                    0,
                    new ArrayList<>())); // find the cheapest path between cities
        }
    }

    private String recursivePathfinding(List<City> cities,
                                      City source,
                                      City destination,
                                      List<Integer> visited,
                                      int total,
                                      List<Integer> costPaths) {
        HashMap<Integer, Integer> directionPrice = source.getDirectionPrice(); // init a var for direction-price
        boolean isVisited = false; // init a var to check if the city has been visited

        // if the name of the source and the name of the direction are the same, then the price is zero
        if (source.getName().equals(destination.getName()))
            return "0";

        for (int k : directionPrice.keySet()) { // get one of the neighbors of the source
            if (k == destination.getId()) // if the id of the neighbor is equal to the id of the destination
                // save the price of the path to find the cheapest price later
                costPaths.add(total + directionPrice.get(k));
            else {
                // check if neighbor has been visited yet
                for (int v : visited)
                    if (k == v) {
                        isVisited = true;
                        break;
                    }

                // if neighbor has been visited yet, then start iteration for another city
                if (isVisited) {
                    isVisited = false;
                    continue;
                }

                visited.add(k); // add to the list the id of the city
                total += directionPrice.get(k); // increase the total of price
                // get the neighbor as a source
                Optional<City> newSource = cities.stream().filter(c -> c.getId() == k).findFirst();

                try {
                    if (newSource.isPresent()) { // if the new source is present check its paths
                        recursivePathfinding(
                                cities,
                                newSource.get(),
                                destination,
                                visited,
                                total,
                                costPaths);

                        total -= directionPrice.get(k); // step back
                    }
                    else
                        throw new Exception(String.format("\n[ERROR] Can't find the city with %s id", k));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        if (costPaths.isEmpty()) // if the list of paths' costs is empty, return nothing
            return "";
        else // else find the min of the paths' cost
            return Collections.min(costPaths).toString();
    }

    private int inputInt() throws Exception {
        String data = new Scanner(System.in).nextLine(); // input of data

        if (!data.matches("[0-9]+"))
            throw new Exception("\n[ERROR] Incorrect data! Use only figures. Try again.\n");

        return Integer.parseInt(data);
    }
}

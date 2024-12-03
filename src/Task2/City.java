package Task2;

import java.util.HashMap;

public class City {
    private int id; // city id
    private String name; // city name
    private int numberOfNeighbors; // number of neighbors of the city
    private HashMap<Integer, Integer> directionPrice; // hashmap for direction-price

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) throws Exception {
        if (!name.matches("[a-zA-Z]+") || name.length() > 10)
            throw new Exception("\n[ERROR] Incorrect data! Use only up to 10 letters (A-Z, a-z). Try again.\n");

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumberOfNeighbors(int numberOfNeighbors) throws Exception {
        if (numberOfNeighbors < 1)
            throw new Exception("Incorrect data! Use only positive number (greater than zero). Try again.");

        this.numberOfNeighbors = numberOfNeighbors;
    }

    public int getNumberOfNeighbors() {
        return numberOfNeighbors;
    }

    public void setDirectionPrice(HashMap<Integer, Integer> directionPrice) {
        this.directionPrice = directionPrice;
    }

    public HashMap<Integer, Integer> getDirectionPrice() {
        return directionPrice;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfNeighbors=" + numberOfNeighbors +
                ", directionPrice=" + directionPrice +
                '}';
    }
}

import java.util.*;
import java.util.stream.*;

public class Filter_Passenger_Bogies_Using_Streams {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        void display() {
            System.out.println(name + " -> Capacity: " + capacity);
        }
    }

    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println(" UC8 - Filter Bogies using Streams");
        System.out.println("===========================================\n");

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));

        System.out.println("All Bogies:");
        for (Bogie b : bogies) {
            b.display();
        }

        List<Bogie> filteredBogies = bogies
                .stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered Bogies (Capacity > 60):");
        for (Bogie b : filteredBogies) {
            b.display();
        }
    }
}
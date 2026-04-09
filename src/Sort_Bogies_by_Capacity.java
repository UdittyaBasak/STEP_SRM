import java.util.*;

public class Sort_Bogies_by_Capacity {

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
        System.out.println(" UC7 - Sort Bogies by Capacity (Comparator)");
        System.out.println("===========================================\n");

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));

        System.out.println("Before Sorting:");
        for (Bogie b : bogies) {
            b.display();
        }

        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("\nAfter Sorting (by Capacity):");
        for (Bogie b : bogies) {
            b.display();
        }
    }
}
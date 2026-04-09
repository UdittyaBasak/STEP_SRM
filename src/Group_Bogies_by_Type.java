import java.util.*;
import java.util.stream.*;

public class Group_Bogies_by_Type {
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
        System.out.println(" UC9 - Group Bogies by Type");
        System.out.println("===========================================\n");

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("AC Chair", 58));

        Map<String, List<Bogie>> groupedBogies = bogies
                .stream()
                .collect(Collectors.groupingBy(b -> b.name));

        for (String key : groupedBogies.keySet()) {
            System.out.println("\n" + key + " Bogies:");
            for (Bogie b : groupedBogies.get(key)) {
                b.display();
            }
        }
    }
}

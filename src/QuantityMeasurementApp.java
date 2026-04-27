public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {
            // Step 1: Same reference check (reflexive)
            if (this == obj) return true;

            // Step 2: Null check and type check
            if (obj == null || getClass() != obj.getClass()) return false;

            // Step 3: Cast safely
            Feet other = (Feet) obj;

            // Step 4: Compare double values properly
            return Double.compare(this.value, other.value) == 0;
        }

        // Optional but recommended when overriding equals
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Main method for demonstration
    public static void main(String[] args) {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        boolean result = feet1.equals(feet2);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + result + ")");
    }
}
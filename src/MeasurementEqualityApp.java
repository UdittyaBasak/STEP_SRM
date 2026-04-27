public class MeasurementEqualityApp {

    // ---------- FEET CLASS ----------
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // ---------- INCHES CLASS ----------
    static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // ---------- STATIC METHODS ----------
    public static boolean compareFeet(double val1, double val2) {
        Feet f1 = new Feet(val1);
        Feet f2 = new Feet(val2);
        return f1.equals(f2);
    }

    public static boolean compareInches(double val1, double val2) {
        Inches i1 = new Inches(val1);
        Inches i2 = new Inches(val2);
        return i1.equals(i2);
    }

    // ---------- MAIN METHOD ----------
    public static void main(String[] args) {

        boolean feetResult = compareFeet(1.0, 1.0);
        boolean inchResult = compareInches(1.0, 1.0);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + feetResult + ")");

        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + inchResult + ")");
    }
}
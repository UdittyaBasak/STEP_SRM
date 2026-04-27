public class UniversalLengthEqualityApp {

    // ---------- ENUM FOR ALL UNITS ----------
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),           // 1 inch = 1/12 feet
        YARD(3.0),                 // 1 yard = 3 feet
        CENTIMETER(0.0328084);     // 1 cm = 0.0328084 feet (derived from 0.393701 inch)

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // ---------- GENERIC QUANTITY CLASS ----------
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }
    }

    // ---------- MAIN METHOD ----------
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q4 = new QuantityLength(36.0, LengthUnit.INCH);

        QuantityLength q5 = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength q6 = new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println("1 yard == 3 feet → " + q1.equals(q2));
        System.out.println("1 yard == 36 inches → " + q3.equals(q4));
        System.out.println("1 cm == 0.393701 inch → " + q5.equals(q6));
    }
}

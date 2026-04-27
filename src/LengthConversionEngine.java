public class LengthConversionEngine {

    // ---------- ENUM ----------
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }

        public double getFactor() {
            return toFeetFactor;
        }
    }

    // ---------- VALUE OBJECT ----------
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        // ---------- INSTANCE CONVERSION ----------
        public QuantityLength convertTo(LengthUnit targetUnit) {
            double converted = convert(this.value, this.unit, targetUnit);
            return new QuantityLength(converted, targetUnit);
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

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ---------- STATIC CONVERSION API ----------
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        validate(value, source);
        if (target == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        // Step 1: convert to base (feet)
        double inFeet = source.toFeet(value);

        // Step 2: convert to target
        return target.fromFeet(inFeet);
    }

    // ---------- VALIDATION ----------
    private static void validate(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
    }

    // ---------- METHOD OVERLOADING DEMO ----------

    // Method 1: raw values
    public static double demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        return convert(value, from, to);
    }

    // Method 2: object-based
    public static QuantityLength demonstrateLengthConversion(QuantityLength quantity, LengthUnit to) {
        return quantity.convertTo(to);
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {

        System.out.println("1 ft → inch: " +
                convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        System.out.println("3 yard → feet: " +
                convert(3.0, LengthUnit.YARD, LengthUnit.FEET));

        System.out.println("36 inch → yard: " +
                convert(36.0, LengthUnit.INCH, LengthUnit.YARD));

        System.out.println("1 cm → inch: " +
                convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH));

        // Instance conversion
        QuantityLength length = new QuantityLength(2.0, LengthUnit.YARD);
        System.out.println("2 yard → inch: " +
                length.convertTo(LengthUnit.INCH));
    }
}
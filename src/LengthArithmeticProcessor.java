public class LengthArithmeticProcessor {

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

        private double toFeet() {
            return unit.toFeet(value);
        }

        // ✅ INSTANCE METHOD (takes ONE argument)
        public QuantityLength add(QuantityLength other) {
            return LengthArithmeticProcessor.add(this, other);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
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

    // ---------- STATIC ADD METHOD ----------
    public static QuantityLength add(QuantityLength first, QuantityLength second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }

        // Convert both to base unit (feet)
        double sumInFeet = first.toFeet() + second.toFeet();

        // Convert back to first operand unit
        double resultValue = first.getUnit().fromFeet(sumInFeet);

        return new QuantityLength(resultValue, first.getUnit());
    }

    // ---------- OVERLOADED METHOD ----------
    public static QuantityLength add(
            double value1, LengthUnit unit1,
            double value2, LengthUnit unit2) {

        QuantityLength q1 = new QuantityLength(value1, unit1);
        QuantityLength q2 = new QuantityLength(value2, unit2);

        return add(q1, q2);
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

    // ---------- MAIN METHOD ----------
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        // ✅ Instance method
        QuantityLength result1 = q1.add(q2);

        // ✅ Static method
        QuantityLength result2 = LengthArithmeticProcessor.add(q1, q2);

        // ✅ Overloaded method
        QuantityLength result3 = LengthArithmeticProcessor.add(
                1.0, LengthUnit.YARD,
                3.0, LengthUnit.FEET
        );

        System.out.println("Instance add: " + result1);   // 2 feet
        System.out.println("Static add: " + result2);     // 2 feet
        System.out.println("Overloaded add: " + result3); // 2 yards

        // More examples
        System.out.println(
                new QuantityLength(12.0, LengthUnit.INCH)
                        .add(new QuantityLength(1.0, LengthUnit.FEET))
        ); // 24 inches

        System.out.println(
                new QuantityLength(2.54, LengthUnit.CENTIMETER)
                        .add(new QuantityLength(1.0, LengthUnit.INCH))
        ); // ~5.08 cm
    }
}
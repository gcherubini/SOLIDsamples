class ReportWithOCP {
    enum Type {
        ORDERS_PER_DAY, CONVERSION_RATES, GENERIC_RATES
    }

    Type type;

    public ReportWithOCP(Type type) {
        this.type = type;
    }

    String generate() {
        // OCP violation
        switch (type) {
            case ORDERS_PER_DAY:
                return "ORDERS_PER_DAY";
            case CONVERSION_RATES:
                return "ORDERS_PER_DAY";
            case GENERIC_RATES:
                return "GEERIC_RATES";
            default:
                return "DEFAULT";
        }
    }
}

// open for extension, but closed for modification";
// [1] that is, such an entity can allow its behaviour to be extended without modifying its source code.

interface Report {
    String generate();
}

class OrdersPerDayReport implements Report {
    public String generate() {
        return "OrdersPerDayReport";
    }
}

class ConversionRatesReport implements Report {
    public String generate() {
        return "ConversionRatesReport";
    }
}

class GenericReport implements Report {
    public String generate() {
        return "GenericReport";
    }
}

public class OpenClosed {
    public static void main(String[] args) {
        ReportWithOCP withOCP = new ReportWithOCP(ReportWithOCP.Type.ORDERS_PER_DAY);
        System.out.println(withOCP.generate());

        OrdersPerDayReport report1 = new OrdersPerDayReport();
        System.out.println(report1.generate());

        ConversionRatesReport report2 = new ConversionRatesReport();
        System.out.println(report2.generate());

        GenericReport report3 = new GenericReport();
        System.out.println(report3.generate());
    }
}

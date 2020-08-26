package day3.Product;

//----------------------
//此处体现SRP-单一职责原则
//----------------------
public class Instrument {
    private String serialNumber;
    private double price;
    private InstrumentSpec spec;

    public Instrument(String serialNumber, double price, InstrumentSpec spec) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.spec = spec;
    }


    public double getPrice() {
        return price;
    }


    public InstrumentSpec getSpec() {
        return spec;
    }

    String getDescription() {
        String instrumentBrand = getOrDefault(spec.getInstrumentBrand());
        String instrumentClass = getOrDefault(spec.getInstrumentClass());
        String instrumentCPU = getOrDefault(spec.getInstrumentCPU());

        return instrumentBrand+ " "+instrumentClass +
                " " + instrumentCPU;
    }

    private String getOrDefault(Enum item) {
        return item == null ? "" : (item.toString() + " ");
    }

    String getPriceInfo() {
        return "  你可以购买 " +
                getOrDefault(spec.getInstrumentBrand()) +
                getOrDefault(spec.getInstrumentClass()) +
                getOrDefault(spec.getInstrumentCPU()) + "for $" +
                getPrice() + "\n---";
    }

    private String getIntro() {
        return this.getDescription() + "\n" +
                this.getPriceInfo();
    }

    public void printIntro() {
        System.out.println(getIntro());
    }

}

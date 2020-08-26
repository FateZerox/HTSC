package day3.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
    private static List<Instrument> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }

    //----------------------
    //此处体现SRP-单一职责原则
    //----------------------
    public void addInstrument(String serialNumber, double price,
                              InstrumentSpec spec) {
        Instrument instrument = new Instrument(serialNumber, price, spec);
        inventory.add(instrument);
    }



    private static List<Instrument> search(InstrumentSpec searchSpec) {
        return inventory.stream()
                .filter(instrument -> instrument.getSpec().matches(searchSpec))
                .collect(Collectors.toList());
    }

    //------------------------
    //此处体现SRP-单一职责原则
    //-----------------------
    public static void searchFor(InstrumentSpec inputSpec) {


        List<Instrument> matchingInstruments = search(inputSpec);

        if (matchingInstruments.isEmpty()) {
            System.out.println("对不起，没有找到您希望的设备.");
            return;
        }

        System.out.println("您可能对该商品感兴趣:");
        matchingInstruments.forEach(Instrument::printIntro);
    }

}

package day3.Product;

import day3.Enums.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

//----------------------
//此处体现SRP-单一职责原则
//----------------------
public class InstrumentSpec  {
    private Map<String, Object> properties = new HashMap<>();

    public InstrumentSpec() {

    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public InstrumentCPU getInstrumentCPU() {
        return (InstrumentCPU) get("InstrumentCPU");
    }

    public InstrumentSpec setInstrumentCPU(InstrumentCPU instrumentCPU) {
        put(instrumentCPU);
        return this;
    }

    public InstrumentClass getInstrumentClass() {
        return (InstrumentClass) get("InstrumentClass");
    }

    public InstrumentSpec setInstrumentClass(InstrumentClass instrumentClass) {
        put(instrumentClass);
        return this;
    }

    public InstrumentBrand getInstrumentBrand() {
        return (InstrumentBrand) get("InstrumentBrand");
    }

    public InstrumentSpec setInstrumentBrand(InstrumentBrand instrumentBrand) {
        put(instrumentBrand);
        return this;
    }

    private String getNameOf(Object property) {
        return property.getClass().getSimpleName();
    }

    Object get(String key) {
        return properties.get(key);
    }

    private void put(Object value) {
        properties.put(getNameOf(value), value);
    }

    boolean matches(InstrumentSpec otherSpec) {
        return otherSpec.properties
                .keySet()
                .stream()
                .allMatch(valuesMatch(otherSpec));
    }

    private Predicate<String> valuesMatch(InstrumentSpec otherSpec) {
        return otherKey -> otherSpec.get(otherKey).equals(properties.get(otherKey));
    }
}

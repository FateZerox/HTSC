package day5.Fliter;

public class ContainCondition extends Condition {
    public ContainCondition(Property property, String input){
        this.expression = "CONTAINS(" +
                property.toString() +
                ","+input + ")";

    }
}

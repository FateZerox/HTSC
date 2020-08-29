package day5.Fliter;

public class EqualCondition extends Condition {
    public EqualCondition(Property property, String input){
        this.expression = property.toString() + "=" +input;
    }
}

package day5.Fliter;

public class NotEqualCondition extends Condition {
    public NotEqualCondition(Property property, String input){
        this.expression = property.toString() + "!=" +input;
    }
}

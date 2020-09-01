public class SingleCondition implements Condition {
    private String data;

    public SingleCondition(String data){
        this.data = data;
    }

    @Override
    public String getSqlDescription() {
        return data;
    }
}

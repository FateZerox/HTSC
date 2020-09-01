import org.junit.Assert;
import org.junit.Test;

public class GenSqlTest {
    //单条件的查询
    @Test
    public void test_singlesql(){
        Assert.assertEquals("select * from table where (CompanyName=HTSC)",
                SQLGenerator.generate_sql("(CompanyName=HTSC)"));
    }

    //and语句查询
    @Test
    public void test_and(){
        Assert.assertEquals("select * from table where " +
                        "(CompanyName=HTSC) and (Country=CHINA)",
                SQLGenerator.generate_sql("(CompanyName=HTSC) and (Country=CHINA)"));
    }

    //or语句查询
    @Test
    public void test_or(){
        Assert.assertEquals("select * from table where " +
                        "(CompanyName=HTSC) or (Country=CHINA)",
                SQLGenerator.generate_sql("(CompanyName=HTSC) or (Country=CHINA)"));
    }

    //not语句查询
    @Test
    public void test_not(){
        Assert.assertEquals("select * from table where " +
                        "(CompanyName=HTSC) or (!(Country=CHINA) and !(Country=USA))",
                SQLGenerator.generate_sql("(CompanyName=HTSC) " +
                        "or (!(Country=CHINA) and !(Country=USA))"));
    }

    //and语句嵌套or语句查询
    @Test
    public void test_and_with_or(){
        Assert.assertEquals("select * from table where " +
                        "(CompanyName=HTSC) and ((Country=CHINA) or (Age>28))",
                SQLGenerator.generate_sql("(CompanyName=HTSC) " +
                        "and ((Country=CHINA) or (Age>28))"));
    }

    //多层嵌套语句查询
    @Test
    public void test_and_or_not(){
        Assert.assertEquals("select * from table where " +
                        "(((CompanyName=HTSC) or (Age>28)) or (Sex=Male)) " +
                        "and !(!(Country=USA) or (Country=CHINA))",
                SQLGenerator.generate_sql("(((CompanyName=HTSC) or (Age>28)) or (Sex=Male)) " +
                        "and !(!(Country=USA) or (Country=CHINA))"));

    }



}

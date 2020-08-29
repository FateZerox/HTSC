package day5.Test;

import static org.junit.Assert.*;

import day5.Fliter.*;
import org.junit.Test;

public class FilterTest {
    //equal测试
    @Test
    public void equalTest(){
        Condition c = new EqualCondition(Property.CONTACT_NAME,"Tom");
        assertEquals("Contact Name=Tom", c.getExpression());
    }

    //not equal测试
    @Test
    public void notEqualTest(){
        Condition c = new NotEqualCondition(Property.CONTACT_NAME,"Tom");
        assertEquals("Contact Name!=Tom", c.getExpression());
    }

    //contains测试
    @Test
    public void containTest(){
        Condition c = new ContainCondition(Property.CONTACT_NAME,"Tom");
        assertEquals("CONTAINS(Contact Name,Tom)", c.getExpression());
    }

    //and测试(equal and equal)
    @Test
    public void andTest(){
        Condition c = new EqualCondition(Property.CONTACT_NAME,"Tom");
        Condition c1 = new EqualCondition(Property.COUNTRY,"CHINA");
        assertEquals("Contact Name=Tom AND Country=CHINA", c.and(c1));
    }

    //and测试2(equal and not equal)
    @Test
    public void andTest2(){
        Condition c = new EqualCondition(Property.CONTACT_NAME,"Tom");
        Condition c1 = new NotEqualCondition(Property.COMPANY_NAME,"alibaba");
        assertEquals("Contact Name=Tom AND Company Name!=alibaba", c.and(c1));
    }

    //and测试3(equal and contains)
    @Test
    public void andTest3(){
        Condition c = new EqualCondition(Property.CONTACT_NAME,"Tom");
        Condition c1 = new ContainCondition(Property.COMPANY_NAME,"alibaba");
        assertEquals("Contact Name=Tom AND CONTAINS(Company Name,alibaba)", c.and(c1));
    }

    //or测试(equal or equal)
    @Test
    public void orTest(){
        Condition c = new EqualCondition(Property.CONTACT_NAME,"Tom");
        Condition c1 = new EqualCondition(Property.COUNTRY,"CHINA");
        assertEquals("Contact Name=Tom OR Country=CHINA", c.or(c1));
    }

    //or测试2(equal or not equal)
    @Test
    public void orTest2(){
        Condition c = new EqualCondition(Property.CONTACT_NAME,"Tom");
        Condition c1 = new NotEqualCondition(Property.COMPANY_NAME,"alibaba");
        assertEquals("Contact Name=Tom OR Company Name!=alibaba", c.or(c1));
    }

    //or测试(equal or contains)
    @Test
    public void orTest3(){
        Condition c = new EqualCondition(Property.CONTACT_NAME,"Tom");
        Condition c1 = new ContainCondition(Property.COUNTRY,"CHINA");
        assertEquals("Contact Name=Tom OR CONTAINS(Country,CHINA)", c.or(c1));
    }

}


import dao.IRoleDao;
import domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleTest {
    static InputStream in;
    static SqlSessionFactoryBuilder builder;
    static SqlSessionFactory factory;
    static SqlSession session;
    static IRoleDao roleDao;

    @Before
    public void setup() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.创建sqlsessionFactory的构建者对象
        builder = new SqlSessionFactoryBuilder();

        //3.使用构建者创建工厂对象SqlSessionFactory
        factory = builder.build(in);

        //4.使用SqlSessionFactory创建SqlSession对象
        session = factory.openSession(true);

        //5.使用SqlSession创建dao接口的代理对象
        roleDao = session.getMapper(IRoleDao.class);
    }

    @After
    public void after() throws Exception {
        //session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        List<Role> roles = roleDao.findAll();

        Assert.assertEquals(3,roles.size());

        for (Role role:roles
             ) {
            System.out.println("--每个角色的信息");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }
}

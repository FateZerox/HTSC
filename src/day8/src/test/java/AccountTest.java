import dao.IAccountDao;
import dao.IUserDao;
import domain.AccountUser;
import domain.QueryVo;
import domain.QueryVoIds;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountTest {

    static InputStream in;
    static SqlSessionFactoryBuilder builder;
    static SqlSessionFactory factory;
    static SqlSession session;
    static IAccountDao accountDao;

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
        accountDao = session.getMapper(IAccountDao.class);
    }

    @After
    public void after() throws Exception {
        //session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        List<AccountUser> accountUsers = accountDao.findAll();
        for (AccountUser au:accountUsers
             ) {
            System.out.println(au);
        }

        assert accountUsers.size() == 3;
    }
}

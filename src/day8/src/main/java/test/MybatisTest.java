package test;

import dao.IUserDao;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.创建sqlsessionFactory的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        //3.使用构建者创建工厂对象SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);

        //4.使用SqlSessionFactory创建SqlSession对象
        SqlSession session = factory.openSession();

        //5.使用SqlSession创建dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);

        //6.使用代理对象执行findAll
        List<User> userList = userDao.findAll();
        for (User user: userList
             ) {
            System.out.println(user);
        }
    }
}

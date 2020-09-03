
import dao.IUserDao;
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

public class UserCURDTest {

    static InputStream in;
    static SqlSessionFactoryBuilder builder;
    static SqlSessionFactory factory;
    static SqlSession session;
    static IUserDao userDao;

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
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void after() throws Exception {
        //session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindOne() {
        User user = userDao.findById(41);
        System.out.println(user);
        Assert.assertEquals("张三",user.getUserName());
    }

    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        assert users.size() == 6;

        for (User user:users
             ) {
            System.out.println(user);
        }
    }

//    @Test
//    public void testSave() {
//        User user = new User();
//        user.setUserName("华泰");
//        user.setUserAddress("南京市建邺区");
//        user.setUserSex("男");
//        user.setUserBirthday(new Date());
//
//        int affectedRows =userDao.saveUser(user);
//
//        Assert.assertEquals(1,affectedRows);
//
//        User saveUser = userDao.findById(user.getUserId());
//        assert saveUser.getUserName().equals("华泰");
//    }

    @Test
    public void testUpdateUser() {
        int id = 46;

        User user = userDao.findById(id);

        user.setUserAddress("北京市海淀区");
        int row = userDao.updateUser(user);

        User savedUser = userDao.findById(id);
        assert savedUser.getUserAddress().equals("北京市海淀区");

        //System.out.println(user.getRefAddress());
        //System.out.println(savedUser.getRefAddress());
    }

//    @Test
//    public void testDeleteUser() {
//        int res = userDao.deleteUser(64);
//        assert res == 1;
//    }

    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%王%");

        assert users.size() == 2;

        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByNameNew() {
        List<User> users = userDao.findByNameNew("王");

        assert users.size() == 2;

        for (User user:users
        ) {
            System.out.println(user);
        }
    }

    @Test
    public void testCount() {
        int res = userDao.count();

        assert res == 6;
    }

    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        vo.setUsername("%王%");
        vo.setAddress("%南京%");

        List<User> users = userDao.findByVo(vo);

        assert users.size() == 1;
    }

    @Test
    public void testFindByVo_WithoutAddress() {
        QueryVo vo = new QueryVo();
        vo.setUsername("%王%");
        vo.setAddress(null);

        List<User> users = userDao.findByVo(vo);

        assert users.size() == 2;
    }

    @Test
    public void testFindInIds() {
        QueryVoIds voids = new QueryVoIds();
        List<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(42);
        ids.add(45);
        ids.add(46);
        voids.setIds(ids);

        List<User> users = userDao.findInIds(voids);

        assert users.size() == 4;
    }
}

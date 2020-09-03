package dao;

import domain.QueryVo;
import domain.QueryVoIds;
import domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();

    User findById(Integer userId);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(Integer userId);

    List<User> findByName(String username);

    List<User> findByNameNew(String username);

    int count();

    //多条件查询
    List<User> findByVo(QueryVo vo);

    List<User> findInIds(QueryVoIds voIds);
}

package test;

import domain.User;

import java.sql.*;

public class MysqlConnector {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/griddroid?characterEncoding=utf-8&serviceTimezone=GMT%288","root","123456");
            String sql = "select * from user";
            preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setString(1,"李四");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                //System.out.println(resultSet.getString("id")+ " " +resultSet);

                User user = new User();
                user.setUserId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setUserSex(resultSet.getString("sex"));
                user.setUserAddress(resultSet.getString("address"));
                user.setUserBirthday(resultSet.getDate("birthday"));
                System.out.println(user.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            //释放资源
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}

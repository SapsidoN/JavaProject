package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.Connection();
             PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS USERS (" +
                     "ID BIGINT PRIMARY KEY AUTO_INCREMENT ," +
                     "NAME VARCHAR(50) NOT NULL," +
                     "LASTNAME VARCHAR(50) NOT NULL," +
                     "AGE TINYINT NOT NULL)")){

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.Connection();
        PreparedStatement statement = connection.prepareStatement("DROP TABLE if exists USERS")){
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.Connection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO  USERS (NAME,LASTNAME,AGE)  values (?,?,?)")){
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.executeUpdate();
            System.out.printf("User с именем %s добавлен", name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public void removeUserById(long id) {
        try (Connection connection = Util.Connection();
        PreparedStatement statement = connection.prepareStatement("DELETE  FROM USERS where id = ?")){
            statement.setLong(1,id);
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.Connection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS") ;
             ResultSet resultSet = statement.executeQuery()){
            List<User> list = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User  user = new User(name,lastName,age);
                list.add(user);
            }
            return list;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.Connection();
        PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE USERS")){
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

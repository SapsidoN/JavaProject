package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();;
        userService.saveUser("Олег","Кудрин", (byte) 27);
        userService.saveUser("Илья","Иванов", (byte) 22);
        userService.saveUser("Паша","Путин", (byte) 30);
        userService.saveUser("Ира","Пушкина", (byte) 43);

        List<User> list =  userService.getAllUsers();
        list.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

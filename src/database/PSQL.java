package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class PSQL {

    static Connection connection;
    public static void makeConnection(String url,String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url,username,password);
    }  
    public static void createTable() throws SQLException{
        var prepared = PSQL.connection.prepareStatement("""
           CREATE TABLE IF NOT EXISTS Employee(
           id serial,
           name text not null,
           surename text,
           dateofbirth date not null,
           departament text,
           salary real not null,
           constraint salary_nonnegative check (salary >= 0));
           """);
       prepared.execute();
     }
    public static void dropTable() throws SQLException{
        var prepared = PSQL.connection.prepareStatement("""
           DROP TABLE employee;
           """);
       prepared.execute();
    }
}

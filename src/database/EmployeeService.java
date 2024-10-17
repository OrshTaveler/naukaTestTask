package database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.EmployeeDAO;

public class EmployeeService {
    
       
      public static void insertEmployee(EmployeeDAO employee) throws SQLException{
        var prepared = PSQL.connection.prepareStatement("INSERT INTO employee(name,surename,dateofbirth,departament,salary) VALUES (?,?,?,?,?)");
        prepared.setString(1, employee.getName());
        prepared.setString(2, employee.getSurename());
        prepared.setDate(3, employee.getDateofBirth());
        prepared.setString(4, employee.getDepartament());
        prepared.setFloat(5, employee.getSalary());
        prepared.execute();
      }
      private static EmployeeDAO sqlToEmployeeDAO(int id, ResultSet resultSet) throws SQLException{
        return new EmployeeDAO(id, resultSet.getString("name"),resultSet.getString("surename"),resultSet.getDate("dateofbirth"),resultSet.getString("departament"),resultSet.getFloat("salary"));
      }
      private static EmployeeDAO sqlToEmployeeDAO(ResultSet resultSet) throws SQLException{
        return new EmployeeDAO(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getString("surename"),resultSet.getDate("dateofbirth"),resultSet.getString("departament"),resultSet.getFloat("salary"));
      }
      public static EmployeeDAO findById(int id) throws SQLException{
        var prepared = PSQL.connection.prepareStatement("SELECT * FROM employee WHERE (id = ?)");
        prepared.setInt(1,id);
        ResultSet resultSet = prepared.executeQuery();
        if(!resultSet.isBeforeFirst()) return null;
        resultSet.next();
        return sqlToEmployeeDAO(id, resultSet);
      }

      public static ArrayList<String> groupByName() throws SQLException{
        var prepared = PSQL.connection.prepareStatement("SELECT name FROM employee GROUP BY employee.name");
        ResultSet resultSet = prepared.executeQuery();
        if(!resultSet.isBeforeFirst()) return null;
        ArrayList<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString("name"));
        }
        return result;
      }
      public static ArrayList<EmployeeDAO> findBetween(Date from, Date to) throws SQLException{
        var prepared = PSQL.connection.prepareStatement("SELECT * FROM employee WHERE dateofbirth BETWEEN ? AND ?");
        prepared.setDate(1, from);
        prepared.setDate(2, to);
        ResultSet resultSet = prepared.executeQuery();
        if(!resultSet.isBeforeFirst()) return null;
        ArrayList<EmployeeDAO> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(sqlToEmployeeDAO(resultSet));
        }
        return result;
      } 
   
}

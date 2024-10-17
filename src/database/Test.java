package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import dao.EmployeeDAO;

public class Test {

     
      public static java.sql.Date stringToDate(String sdate) throws ParseException{
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = sdf1.parse(sdate);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            return sqlDate;
      }

      public static void fillWithData() throws FileNotFoundException,ParseException,SQLException{
       
        File tests = new File("testinfo.txt");
        Scanner myReader = new Scanner(tests);
        while (myReader.hasNextLine()) {
            String[] data = myReader.nextLine().split(",");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = sdf1.parse(data[2]);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            EmployeeService.insertEmployee(new EmployeeDAO(data[0], data[1],sqlDate, data[3],Float.parseFloat(data[4])));
        }
        myReader.close();
      }

      public static void tests() throws SQLException, FileNotFoundException, ParseException{
            PSQL.createTable();
            fillWithData();
            System.out.println("---------------FIND BY ID TEST------------------");
            for(int i =  40; i <= 60 ; i++){
                EmployeeDAO res = EmployeeService.findById(i);
                if(res == null){
                    System.out.println("ID "+i+"| IT IS NOT THERE ");
                    continue;
                }
                System.out.print(res);
                System.out.println("| IS THERE ");
             
            }
            System.out.println("---------------GROUP BY TEST------------------");
            for(String res: EmployeeService.groupByName()){
                System.out.println(res);
            }
            System.out.println("---------------FIND BETWEEN TEST------------------");
            for(EmployeeDAO eDao: EmployeeService.findBetween(stringToDate("01-01-1990"), stringToDate("01-01-2010"))){
                System.out.println(eDao);
            }


            PSQL.dropTable();
      }

}

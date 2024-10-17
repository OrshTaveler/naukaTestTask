package dao;

import java.sql.Date;

public class EmployeeDAO {

    private int id;
    private String name;
    private String surename;
    private Date dateofbirth;
    private String departament;
    private float salary;

    public EmployeeDAO(int id,String name, String surename,Date dateofbirth,String departament,float salary){
        this.id = id;
        this.name = name;
        this.surename = surename;
        this.dateofbirth = dateofbirth;
        this.departament = departament;
        this.salary = salary;
    }

    public EmployeeDAO(String name, String surename,Date dateofbirth,String departament,float salary){
        this.name = name;
        this.surename = surename;
        this.dateofbirth = dateofbirth;
        this.departament = departament;
        this.salary = salary;
    }

    @Override
    public String toString(){
        String employeeString = "id: %s | name: %s | surename: %s | dateofbirth: %s | departament: %s | salary: %f";
        return employeeString.formatted(this.id, this.name, this.surename, this.dateofbirth.toString(), this.departament, this.salary);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    public String getSurename(){
        return this.surename;
    }
    public Date getDateofBirth(){
        return this.dateofbirth;
    }
    public String getDepartament(){
        return this.departament;
    }
    public float getSalary(){
        return this.salary;
    }
}

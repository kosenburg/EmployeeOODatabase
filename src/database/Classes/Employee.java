package database.Classes;

import database.DataStructures.Date;

import java.util.ArrayList;

public class Employee implements DatabaseClass{
private String firstName;
private String middleInitial;
private String lastName;
private String ssn;
private Date birthDate;
private String address;
private char sex;
private int salary;
private ArrayList<Employee> supervisees;
private ArrayList<Project> projects;
private Department department;
private ArrayList<Dependent> dependents;

    public Employee(String firstName, String lastName, String middleInitial, String ssn, char sex){
        setFirstName(firstName);
        setLastName(lastName);
        setMidInit(middleInitial);
        setSsn(ssn);
        setSex(sex);
        setSupervisees();
        setDependents();
    }



    public void addSupervisee(Employee employee) {
        supervisees.add(employee);
    }

    public Employee getSupervisee(String employeeSsn) {
        for (Employee employee:supervisees) {
            if (employee.getSsn().equals(employeeSsn)) {
                return employee;
            }
        }
        return null;
    }

    public boolean isSuperviser() {
        return (supervisees.size() > 0);
    }

    public String getFirstName(){
    return firstName;
    }

    private void setFirstName(String fName){
    this.firstName = fName;
    }


    public String getMidInit(){
    return middleInitial;
    }

    private void setMidInit(String mInit){
    this.middleInitial = mInit;
    }


    public String getLastName(){
    return lastName;
    }

    public void setLastName(String lName){
    this.lastName = lName;
    }

    public String getSsn(){
    return ssn;
    }

    private void setSsn(String ssn){
    this.ssn = ssn;
    }

    public Date getBirthDate(){
    return birthDate;
    }

    public void setBirthDate(Date bDate){
    this.birthDate = bDate;
    }

    public String getAddress(){
    return address;
    }

    public void setAddress(String address){
    this.address = address;
    }

    public char getSex(){
    return sex;
    }

    public void setSex(char sex){
    this.sex = this.sex;
    }


    public int getSalary(){
    return salary;
    }

    public void setSalary(int salary){
    this.salary = salary;
    }

    public void setSupervisees() {
        this.supervisees = new ArrayList<>();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ArrayList<Dependent> getDependents() {
        return dependents;
    }

    public void addDependent(Dependent dependent) {
        dependents.add(dependent);
    }

    public void setDependents() {
        this.dependents = new ArrayList<>();
    }
}
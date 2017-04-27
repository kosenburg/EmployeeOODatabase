package database.Classes;

import database.DataStructures.Date;

import java.util.ArrayList;

public class Employee extends DatabaseClass {
private String firstName;
private String middleInitial;
private String lastName;
private String ssn;
private Date birthDate;
private String address;
private String sex;
private int salary;
private ArrayList<Employee> supervisees;
private ArrayList<Project> projects;
private Department department;
private ArrayList<Dependent> dependents;

    public Employee(String firstName, String lastName, String middleInitial, String ssn, String sex){
        setFirstName(firstName);
        setLastName(lastName);
        setMidInit(middleInitial);
        setSsn(ssn);
        setSex(sex);
        setSupervisees();
        setDependents();
        setProjets();
    }

    private void setProjets() {
        projects = new ArrayList<>();
    }

    public Employee() {}

    public void addSupervisee(Employee employee) {
        supervisees.add(employee);
    }

    public ArrayList<Employee> getSupervisees() {
        return supervisees;
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

    private void setLastName(String lName){
    this.lastName = lName;
    }

    public String getSsn(){
    return ssn;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    private void setSsn(String ssn){
    this.ssn = ssn;
    }

    public String getBirthDate(){
        return birthDate.getDate();
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

    public String getSex(){
    return sex;
    }

    private void setSex(String sex){
    this.sex = sex;
    }

    public int getSalary(){
    return salary;
    }

    public void setSalary(int salary){
    this.salary = salary;
    }

    private void setSupervisees() {
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

    private void setDependents() {
        this.dependents = new ArrayList<>();
    }
}
package database.Classes;

import database.DataStructures.Date;

import java.util.ArrayList;


public class Department extends DatabaseClass {
private String name;
private String number;
private Employee manager;
private Date managerStartDate;
private String location;
private ArrayList<Project> projects;
private ArrayList<Employee> employees;



	public Department(String name, String number, String location) {
		setName(name);
		setNumber(number);
		setLocation(location);
		setEmployees();
		setProjects();
	}


	public void addEmployee(Employee employee) {
		employees.add(employee);
		employee.setDepartment(this);
	}

	public Employee getEmployee(String ssn) {
		for (Employee employee: employees) {
			if (employee.getSsn().equals(ssn)) {
				return employee;
			}
		}
		return null;
	}

	public String getName(){
	return name;
	}

	public void setName(String deptName){
	this.name = deptName;
	}

	public String getNumber(){
	return number;
	}

	public void setNumber(String deptNumber){
	this.number = deptNumber;
	}

	public String getManagerSsn(){
	return manager.getSsn();
	}

	public void setManager(Employee manager, Date startDate){
	this.manager = manager;
	setManagerStartDate(startDate);
	}

	public String getManagerStartDate(){
	return managerStartDate.getDate();
	}

	private void setManagerStartDate(Date managerStartDate){
	this.managerStartDate = managerStartDate;
	}


	public String getLocation(){
		return location;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public Project getProject(String name) {
		for (Project project: projects) {
			if (project.getName().equals(name)) {
				return project;
			}
		}
		return null;
	}


	public void addProject(Project project) {
		projects.add(project);
	}

	private void setProjects() {
		this.projects = new ArrayList<>();
	}

	private void setEmployees() {
		this.employees = new ArrayList<>();
	}
}
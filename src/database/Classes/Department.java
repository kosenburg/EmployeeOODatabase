package database.Classes;

import database.DataStructures.Date;

import java.util.ArrayList;


public class Department implements DatabaseClass {
private String name;
private int number;
private Employee manager;
private Date managerStartDate;
private String location;
private ArrayList<Project> projects;
private ArrayList<Employee> employees;



	public Department(String name, int number, String location){
		setDeptName(name);
		setDeptNumber(number);
		setDeptLocation(location);
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

	public int getNumberOfEmployees() {
		return employees.size();
	}

	public String getDeptName(){
	return name;
	}

	public void setDeptName(String deptName){
	this.name = deptName;
	}


	public int getDeptNumber(){
	return number;
	}

	public void setDeptNumber(int deptNumber){
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


	public String getDeptLocation(){
		return location;
	}

	public void setDeptLocation(String location){
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

	public String displayAllProjects() {
		StringBuilder builder = new StringBuilder();

		for (Project project: projects) {
			builder.append(project.getName() + ", ");
		}

		String finalList = builder.toString();
		return finalList.substring(0,finalList.length() - 2);
	}


	public void addProject(Project project) {
		projects.add(project);
	}

	public void setProjects() {
		this.projects = new ArrayList<>();
	}

	public void setEmployees() {
		this.employees = new ArrayList<>();
	}
}
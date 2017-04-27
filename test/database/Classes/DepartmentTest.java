package database.Classes;

import database.DataStructures.Date;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Test
    public void addEmployee() throws Exception {
        Employee employee = new Employee("Jon","Smith","M","222-33-1122","M");
        Date date = new Date("03/24/2016");
        Department department = new Department("myDepartment", "600","here,there,2213");
        department.addEmployee(employee);

        //Assert.assertEquals("243-77-1324",department.getEmployee(employee.getSsn()).getSsn()); // Failing test
        Assert.assertEquals(employee.getSsn(),department.getEmployee(employee.getSsn()).getSsn());
    }


    @Test
    public void getDeptName() throws Exception {
        String departmentName = "myDepartment";
        Department department = new Department(departmentName, "600","here,there,2213");
        Assert.assertEquals(departmentName, department.getName());
    }

    @Test
    public void getDeptNumber() throws Exception {
        String departmentNumber = "123";
        Department department = new Department("myDepartment", departmentNumber,"here,there,2213");
        Assert.assertEquals(departmentNumber, department.getNumber());
    }

    @Test
    public void getManagerSsnAndStartDate() throws Exception {
        String ssn = "222-33-1122";
        Date startDate = new Date("03/24/2017");
        Department department = new Department("myDepartment", "123","here,there,2213");
        department.setManager(new Employee("Jon","Smith","M",ssn,"M"),startDate);
        Assert.assertEquals(ssn,department.getManagerSsn());
        Assert.assertEquals(startDate.getDate(), department.getManagerStartDate());
    }

    @Test
    public void getDeptLocation() throws Exception {
        String location = "here,there,2213";
        Department department = new Department("myDepartment", "123",location);
        Assert.assertEquals(location,department.getLocation());
    }

    @Test
    public void getAndAddAndDisplayAllProject() throws Exception {
        Department department = new Department("myDepartment", "123","here,there,2213");
        Project project1 = new Project("a place", 632,"Project1");
        Project project2 = new Project("a place", 632,"Project2");
        department.addProject(project1);
        department.addProject(project2);

        Assert.assertEquals(project1.getName(), department.getProject(project1.getName()).getName());
    }

}
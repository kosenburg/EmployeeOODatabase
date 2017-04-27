package database.Classes;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by blakejoynes on 3/28/17.
 */
public class EmployeeTest {
    @Test
    public void addSupervisee() throws Exception {

    }

    @Test
    public void getSupervisee() throws Exception {

    }

    @Test
    public void isSuperviser() throws Exception {

    }

    @Test
    public void getFirstName() throws Exception {
        Employee employee = new Employee("Jon","Smith","M","222-33-1122",'M');

        Assert.assertEquals("Jon",employee.getFirstName());
    }

    @Test
    public void getMidInit() throws Exception {
        Employee employee = new Employee("Jon","Smith","M","222-33-1122",'M');

        Assert.assertEquals("M",employee.getMidInit());
    }

    @Test
    public void getLastName() throws Exception {
        Employee employee = new Employee("Jon","Smith","M","222-33-1122",'M');

        Assert.assertEquals("Smith",employee.getLastName());
    }

    @Test
    public void getSsn() throws Exception {
        Employee employee = new Employee("Jon","Smith","M","222-33-1122",'M');

        Assert.assertEquals("222-33-1122",employee.getSsn());
    }

    @Test
    public void getBirthDate() throws Exception {

    }

    @Test
    public void getAddress() throws Exception {

    }

    @Test
    public void getSex() throws Exception {

    }

    @Test
    public void getSalary() throws Exception {

    }

    @Test
    public void getDepartment() throws Exception {

    }

    @Test
    public void getDependents() throws Exception {

    }

    @Test
    public void addDependent() throws Exception {

    }

}
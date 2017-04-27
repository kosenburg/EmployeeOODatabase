package database.Classes;

import database.DataStructures.Date;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 3/25/2017.
 */
public class DependentTest {
    @Test
    public void getSex() throws Exception {
        Employee employee = new Employee("Jon","Smith","M","222-33-1122","M");
        Dependent dependent = new Dependent("John", new Date("03/23/1223"),"M",employee);

        Assert.assertEquals('M', dependent.getSex());
    }

    @Test
    public void getName() throws Exception {
        Employee employee = new Employee("Jon","Smith","M","222-33-1122","M");
        Dependent dependent = new Dependent("John", new Date("03/23/1223"),"M",employee);

        Assert.assertEquals("John", dependent.getName());
    }

    @Test
    public void getRelationship() throws Exception {
        Employee employee = new Employee("Jon","Smith","M","222-33-1122","M");
        Dependent dependent = new Dependent("John", new Date("03/23/1223"),"M",employee);

        Assert.assertEquals(employee, dependent.getRelationship());
    }

}
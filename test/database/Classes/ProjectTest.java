package database.Classes;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 3/25/2017.
 */
public class ProjectTest {
    @Test
    public void getLocation() throws Exception {
        String location = "here,there,1234";
        Project project = new Project(location,"22","Project1");

        Assert.assertEquals(location, project.getLocation());

    }

    @Test
    public void getNumber() throws Exception {
        String number = "22";
        Project project = new Project("fsfs,sfsf,23",number,"Project1");
        Assert.assertEquals(number,project.getNumber());
    }

    @Test
    public void getName() throws Exception {
        String name = "Project1";
        Project project = new Project("fsfs,sfsf,23","22",name);

        Assert.assertEquals(name, project.getName());

    }

    @Test
    public void addAndGetEmployee() throws Exception {
        Project project = new Project("fsfs,sfsf,23","22","Project1");
        Employee employee1 = new Employee("Jon","Smith","M","222-33-1122","M");
        Employee employee2 = new Employee("Joe","Smith","M","222-34-1122","M");

        project.addEmployee(employee1);
        project.addEmployee(employee2);



    }

}
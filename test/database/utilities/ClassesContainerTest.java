package database.utilities;

import database.Classes.Department;
import database.Classes.Dependent;
import database.Classes.Employee;
import database.Classes.Project;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 3/30/2017.
 */
public class ClassesContainerTest {
    @Test
    public void addClass() throws Exception {
        ClassesContainer container = new ClassesContainer();

        /*
        container.addClass(new Dependent());
        container.addClass(new Department());
        container.addClass(new Project());
        container.addClass(new Employee());

        Assert.assertEquals(2,container.getClassList("employee").size());
        */
    }

    @Test
    public void getClassList() throws Exception {
        ClassesContainer container = new ClassesContainer();

        /*
        container.addClass(new Dependent());
        container.addClass(new Department());
        container.addClass(new Project());
        container.addClass(new Employee());

        Assert.assertEquals(2,container.getClassList("employee").size());
        Assert.assertEquals(2,container.getClassList("project").size());
        Assert.assertEquals(2,container.getClassList("dependent").size());
        Assert.assertEquals(2,container.getClassList("department").size());
        */
    }

}
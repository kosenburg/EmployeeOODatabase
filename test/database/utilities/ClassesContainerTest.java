package database.utilities;

import database.Classes.*;
import database.utilities.object_utilities.ClassesContainer;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Kevin on 3/30/2017.
 */
public class ClassesContainerTest {
    @Test
    public void addClass() throws Exception {
        ClassesContainer container = new ClassesContainer();


        ClassesContainer.addClass(new Dependent("John", "F", new Employee()));
        /*container.addClass(new Department());
        container.addClass(new Project());
        container.addClass(new Employee());

        Assert.assertEquals(2,container.getClassList("employee").size());
        */

        ArrayList<DatabaseClass> list = ClassesContainer.getClassList("dependent");

        System.out.println(list.size());

        Dependent dependent = (Dependent) list.get(0);

        String userInput = "name=Jim";

        System.out.println(dependent.getName());
        String[] stuff = userInput.split("=");
        for (Method method: dependent.getClass().getMethods()) {
            if (method.getName().toLowerCase().contains("set" + stuff[0])) {
                Method innerMethod = dependent.getClass().getMethod(method.getName(), method.getParameterTypes());
                innerMethod.invoke(dependent, stuff[1]);
            }

        }
        System.out.println(dependent.getName());

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
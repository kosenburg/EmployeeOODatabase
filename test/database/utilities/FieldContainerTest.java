package database.utilities;

import database.Classes.Project;
import database.utilities.object_utilities.FieldContainer;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by Kevin on 3/30/2017.
 */
public class FieldContainerTest {
    @Test
    public void getFields() throws Exception {
        Field[] list = FieldContainer.getFields("project");

        Assert.assertArrayEquals(Project.class.getDeclaredFields(),list);
    }

    @Test
    public void isInFields() throws Exception {
        Assert.assertTrue(FieldContainer.isInFields("name","project"));
    }

}
package database.Classes;


import database.DataStructures.Date;

/**
 * Created by Kevin on 3/2/2017.
 */
public class Dependent implements DatabaseClass{
    private char sex;
    private String name;
    private Date birthDate;
    private Employee relationship;

    public Dependent(String name, Date birthDate, char sex, Employee relationship) { // forces the constraint that a dependent must have an employee
        setName(name);
        setBirthDate(birthDate);
        setSex(sex);
        setRelationship(relationship);

    }

    public Dependent(String name, String sex, Employee relationship) {
        this(name, new Date("##/##/####"),' ',relationship);
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Employee getRelationship() {
        return relationship;
    }

    public void setRelationship(Employee relationship) {
        this.relationship = relationship;
    }
}

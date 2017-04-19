package database.Classes;


import database.DataStructures.Date;

/**
 * Created by Kevin on 3/2/2017.
 */
public class Dependent extends DatabaseClass{
    private String sex;
    private String name;
    private Date birthDate;
    private Employee relationship;

    public Dependent(String name, Date birthDate, String sex, Employee relationship) { // forces the constraint that a dependent must have an employee
        setName(name);
        setBirthDate(birthDate);
        setSex(sex);
        setRelationship(relationship);

    }

    public Dependent() {

    }

    public Dependent(String name, String sex, Employee relationship) {
        this(name, new Date("##/##/####"),"",relationship);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

package database.Classes;

import database.DataStructures.Date;

/**
 * Created by Blake on 3/2/2017.
 */

public class Employee{
private String fName;
private String mInit;
private String lName;
private String ssn;
private Date bDate;
private String address;
private String sex;
private int salary;
private String supervisorSsn;
private int departmentNum;

public Employee(){

}

public String getFirstName(){
return fName;
}

public void setFirstName(String fName){
this.fName = fName;
}


public String getMidInit(){
return mInit;
}

public void setMidInit(String mInit){
this.mInit = mInit;
}


public String getLastName(){
return lName;
}

public void setLastName(String lName){
this.lName = lName;
}

public String getSsn(){
return ssn;
}

public void setSsn(String ssn){
this.ssn = ssn;
}

public Date getBirthDate(){
return bDate;
}

public void setBirthDate(Date bDate){
this.bDate = bDate;
}

public String getAddress(){
return address;
}

public void setAddress(String address){
this.address = address;
}

public String getSex(){
return sex;
}

public void setSex(){
this.sex = sex;
}


public int getSalary(){
return salary;
}

public void setSalary(int salary){
this.salary = salary;
}

public String getSupervisorSsn(){
return supervisorSsn;
}

public void setSupervisorSsn(String supervisorSsn){
this.supervisorSsn = supervisorSsn;
}


public int getDepartment(){
return departmentNum;
}

public void setDepartment(int department){
this.departmentNum = department;
}


}
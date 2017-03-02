package database.Classes;

/**
 * Created by Blake on 3/2/2017.
 */

public class Department(){
private String deptName;
private int deptNumber;
private String managerSsn;
private Date managerStartDate;
private String location;

public Department(){

}


public String getDeptName(){
return deptName;
}

public void setDeptName(String deptName){
this.deptName = deptName;
}


public int getDeptNumber(){
return deptNumber;
}

public void setDeptNumber(int deptNumber){
this.deptNumber = deptNumber;
}

public String getManagerSsn(){
return managerSsn;
}

public void setManagerSsn(String managerSsn){
this.managerSsn = managerSsn;
}

public Date getManagerStartDate(){
return managerStartDate;
}

public void setManagerStartDate(Date managerStartDate){
this.managerStartDate = managerStartDate;
}


public String getDeptLocation(){
	return location;
}

public void setDeptLocation(String location){
	this.location = location;
}



}
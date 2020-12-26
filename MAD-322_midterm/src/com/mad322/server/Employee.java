
package com.mad322.server;

public class Employee 
{
   private int EMP_ID;
   private String END_DATE;
   private String FIRST_NAME;
   private String LAST_NAME;
   private String START_DATE;
   private String TITLE;
   private int ASSIGNED_BRANCH_ID;
   private int DEPT_ID;
   private int SUPERIOR_EMP_ID;
public int getEMP_ID() {
	return EMP_ID;
}
public void setEMP_ID(int eMP_ID) {
	EMP_ID = eMP_ID;
}
public String getEND_DATE() {
	return END_DATE;
}
public void setEND_DATE(String eND_DATE) {
	END_DATE = eND_DATE;
}
public String getFIRST_NAME() {
	return FIRST_NAME;
}
public void setFIRST_NAME(String fIRST_NAME) {
	FIRST_NAME = fIRST_NAME;
}
public String getLAST_NAME() {
	return LAST_NAME;
}
public void setLAST_NAME(String lAST_NAME) {
	LAST_NAME = lAST_NAME;
}
public String getSTART_DATE() {
	return START_DATE;
}
public void setSTART_DATE(String sTART_DATE) {
	START_DATE = sTART_DATE;
}
public String getTITLE() {
	return TITLE;
}
public void setTITLE(String tITLE) {
	TITLE = tITLE;
}
public int getASSIGNED_BRANCH_ID() {
	return ASSIGNED_BRANCH_ID;
}
public void setASSIGNED_BRANCH_ID(int aSSIGNED_BRANCH_ID) {
	ASSIGNED_BRANCH_ID = aSSIGNED_BRANCH_ID;
}
public int getDEPT_ID() {
	return DEPT_ID;
}
public void setDEPT_ID(int dEPT_ID) {
	DEPT_ID = dEPT_ID;
}
public int getSUPERIOR_EMP_ID() {
	return SUPERIOR_EMP_ID;
}
public void setSUPERIOR_EMP_ID(int sUPERIOR_EMP_ID) {
	SUPERIOR_EMP_ID = sUPERIOR_EMP_ID;
}
@Override
public String toString() {
	return "Employees [Employee ID=" + EMP_ID + ", End Date=" + END_DATE + ", First Name=" + FIRST_NAME
			+ ", Last Name=" + LAST_NAME + ", Start Date=" + START_DATE + ", Title =" + TITLE + ", Assigned Branch ID="
			+ ASSIGNED_BRANCH_ID + ", Department ID=" +DEPT_ID + ", Superior Employee ID=" +SUPERIOR_EMP_ID+"]";
}


}

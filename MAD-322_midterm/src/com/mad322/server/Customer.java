package com.mad322.server;

public class Customer 
{
	private int CUST_ID;
	private String ADDRESS;
	private String CITY;
	private String CUST_TYPE_CD;
	private String FED_ID;
	private int POSTAL_CODE;
	private String STATE;
	
	
	public int getCUST_ID() {
		return CUST_ID;
	}
	public void setCUST_ID(int cUST_ID) {
		CUST_ID = cUST_ID;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getCUST_TYPE_CD() {
		return CUST_TYPE_CD;
	}

	public void setCUST_TYPE_CD(String cUST_TYPE_CD) {
		CUST_TYPE_CD = cUST_TYPE_CD;
	}

	public String getFED_ID() {
		return FED_ID;
	}

	public void setFED_ID(String fED_ID) {
		FED_ID = fED_ID;
	}

	public int getPOSTAL_CODE() {
		return POSTAL_CODE;
	}

	public void setPOSTAL_CODE(int pOSTAL_CODE) {
		POSTAL_CODE = pOSTAL_CODE;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}




	
	
	
	
	@Override
	public String toString() {
		return "Employees [Customer ID=" + CUST_ID + ", Address=" + ADDRESS + ", City=" + CITY
				+ ", Customer type=" + CUST_TYPE_CD + ", FED ID=" + FED_ID + ", Postal Code=" + POSTAL_CODE + ", State="
				+ STATE + "]";
	}
}

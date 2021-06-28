package com.Oops;

public interface EmpWageInterface {
    public void addCompanyWage(String companyName, int max_working_hrs, int days_in_month, int wage_per_hr);
    public void computeEmpWage();
    public int getTotalWage(String company);
}
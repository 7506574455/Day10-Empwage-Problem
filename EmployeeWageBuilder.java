package com.Oops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Refactor to get total wage when queried by the company.
 */

public class EmployeeWageBuilder implements EmpWageInterface {
    public static final int IS_FUllTime = 1;
    public static final int IS_PARTTIME = 2;
    public static final int IS_ABSENT = 0;

    public int numOfCompany = 0;
    public ArrayList<EmployeeWage> companyEmpWageList;
    private Map<String, EmployeeWage> companyToEmpWage;

    public static void main(String[] args) {
        EmployeeWageBuilder employeeWageBuilder = new EmployeeWageBuilder();
        employeeWageBuilder.addCompanyWage("d-mart", 100, 20, 100);
        employeeWageBuilder.addCompanyWage("jio-mart", 150, 25, 110);
        employeeWageBuilder.computeEmpWage();
        System.out.println("Total employee wage for d-mart " + employeeWageBuilder.getTotalWage("jio-mart"));
    }

    public EmployeeWageBuilder() {
        companyEmpWageList = new ArrayList<>();
        companyToEmpWage = new HashMap<>();
    }

    @Override
    public void addCompanyWage(String companyName, int max_working_hrs, int days_in_month, int wage_per_hr) {
        EmployeeWage employeeWage = new EmployeeWage(companyName, max_working_hrs, days_in_month, wage_per_hr);
        companyEmpWageList.add(employeeWage);
        companyToEmpWage.put(companyName, employeeWage);
    }

    @Override
    public void computeEmpWage() {
        for (int i = 0; i < companyEmpWageList.size(); i++) {
            EmployeeWage employeeWage = companyEmpWageList.get(i);
            employeeWage.setTotalWage(this.computeEmpWage(employeeWage));
            System.out.println(employeeWage);
        }
    }

    @Override
    public int getTotalWage(String company) {
        return companyToEmpWage.get(company).totalWage;
    }

    private int computeEmpWage(EmployeeWage employeeWage) {
        int working_hr = 0;
        int total_working_hr = 0;
        int monthly_total_wage = 0;
        ArrayList<Integer> daily_wage_array = new ArrayList<>();
        int days = 0;
        while (days < employeeWage.days_in_month && total_working_hr < employeeWage.max_working_hrs) {
            days++;
            int emp_check = (int) (Math.floor(Math.random() * 10)) % 3;
            switch (emp_check) {
                case IS_FUllTime -> {
                    working_hr = 8;
                }
                case IS_PARTTIME -> {
                    working_hr = 4;
                }
                case IS_ABSENT -> {
                    working_hr = 0;
                }
            }
            total_working_hr += working_hr;
            daily_wage_array.add(working_hr*employeeWage.wage_per_hr);
        }
        monthly_total_wage = total_working_hr * employeeWage.wage_per_hr;
        return monthly_total_wage;
    }
}
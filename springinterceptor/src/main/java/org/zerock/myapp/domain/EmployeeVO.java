package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Value;


@Value
public class EmployeeVO {
	// 1st. method
//	private Integer EMPLOYEE_ID;
//	private String FIRST_NAME;
//	private String LAST_NAME;
//	private String EMAIL;
//	private String PHONE_NUMBER;
//	private Date HIRE_DATE;
//	private String JOB_ID;
//	private Double SALARY;
//	private Double COMMISSION_PCT;
//	private Integer MANAGER_ID;
//	private Integer DEPARTMENT_ID;

	// 2nd. method
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private Double salary;
	private Double commissionPct;
	private Integer managerId;
	private Integer departmentId;
	
	
	
} // end class

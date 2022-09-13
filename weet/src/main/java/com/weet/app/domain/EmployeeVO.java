package com.weet.app.domain;

import java.util.Date;

import lombok.Value;

@Value
public class EmployeeVO {
	
	// + VO에서는 NULL이 들어올 수 있기에,
	// + 반드시 참조타입을 사용해야 한다.
	// + VO는 읽기 전용이기에 getter만 생성된다.
	
	// ============================
		// employees 테이블에 있는 스키마 등록
	// ============================
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

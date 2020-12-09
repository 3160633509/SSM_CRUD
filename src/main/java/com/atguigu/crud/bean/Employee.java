package com.atguigu.crud.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

public class Employee {
    private Integer empId;

    
    @Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})",
    		message="�û���������6-16λ���ֺ���ĸ����ϻ���2-5λ����")
    private String empName;

    
    //java��������б����Ҫ����ת�壬д��\\   ^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$
    @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",
    		message="�����ʽ����ȷ"
    		)
    private String email;
    
    
    private String gender;

   

    private Integer dId;
    
  //ϣ����ѯԱ����ʱ������ϢҲ�ǲ�ѯ�õ�
    private Department department;

    
   

	
	public Employee(Integer empId, String empName, String gender, String email,
		Integer dId, Department department) {
	super();
	this.empId = empId;
	this.empName = empName;
	this.gender = gender;
	this.email = email;
	this.dId = dId;
	this.department = department;
}


	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Department getDepartment() {
		return department;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName
				+ ", gender=" + gender + ", email=" + email + ", dId=" + dId
				+ ", department=" + department + "]";
	}


	public Integer getEmpId() {
		return empId;
	}


	public void setEmpId(Integer empId) {
		this.empId = empId;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getdId() {
		return dId;
	}


	public void setdId(Integer dId) {
		this.dId = dId;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


}
package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.DepartmentExample;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.bean.EmployeeExample.Criteria;
import com.atguigu.crud.dao.DepartmentMapper;




@Service
public class DepartmentService {

	@Autowired
	private DepartmentMapper departmentmapper;
	
	public List<Department> getDepts() {
		List<Department> list= departmentmapper.selectByExample(null);
	
		return list;
	}

	

}

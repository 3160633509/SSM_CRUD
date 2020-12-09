package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.bean.EmployeeExample.Criteria;
import com.atguigu.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {

	
	@Autowired
	EmployeeMapper  employeeMapper;
	
	//查詢所有員工
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
		
	}

	public void saveEmployee(Employee employee) {
		employeeMapper.insertSelective(employee);
		
	}

	//检验用户名是否可用
	/***
	 * 如果当前没有这条记录  count==0就会返回true,当前姓名可用
	 * 否则 false，当前姓名不可用
	 *
	 */
	public boolean checkUser(String empName) {
		//本来就有这个记录，就返回0，没有就返回大于0
		EmployeeExample example=new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count==0;
	}

	//按照员工id查询员工
	public Employee getEmp(Integer id) {
		Employee employee= employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	
	//员工保存更新
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}

	
	//员工删除
	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
		
	}

	public void deleteBatch(List<Integer> ids) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//delete from xx where emp_id in (1,2,3)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
		
	}

}

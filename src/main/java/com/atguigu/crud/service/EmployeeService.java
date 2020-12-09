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
	
	//��ԃ���ІT��
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
		
	}

	public void saveEmployee(Employee employee) {
		employeeMapper.insertSelective(employee);
		
	}

	//�����û����Ƿ����
	/***
	 * �����ǰû��������¼  count==0�ͻ᷵��true,��ǰ��������
	 * ���� false����ǰ����������
	 *
	 */
	public boolean checkUser(String empName) {
		//�������������¼���ͷ���0��û�оͷ��ش���0
		EmployeeExample example=new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count==0;
	}

	//����Ա��id��ѯԱ��
	public Employee getEmp(Integer id) {
		Employee employee= employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	
	//Ա���������
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}

	
	//Ա��ɾ��
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

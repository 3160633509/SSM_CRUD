package com.atguigu.crud.test;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper; 
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCrud(){
		//���Բ���mapper
		/**
		 * 1.����springIOC����
		 * 2.����������
		 *	 ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
			DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
	
		 * �Ƽ�spring����Ŀʹ��spring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
		 * 1.����springTest���
		 * 	2.@ContextConfigurationָ��spring�����ļ���λ��
		 * 3.ֱ��2AutowiredҪʹ�õ��������
		 * 
		 * 
		 * 
		 * 
		 */
		

			System.out.println(departmentMapper);
			
			
			
			
			
			
			
			
			//1.����ׂ����T
//			departmentMapper.insertSelective(new Department(null,"�_�l��"));
//			departmentMapper.insertSelective(new Department(null,"�yԇ��"));
			//2.���ɆT���������yԇ�T������
			
//			employeeMapper.insertSelective(new Employee(null,"jenry","M","jenry@atguigu.com",1));
			//3.������������T��  ����  ʹ�ÿ��Ԉ�������������sqlsession
//			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//			for(int i=0;i<1000;i++){
//				
//			String uid = UUID.randomUUID().toString().substring(0,5)+i;
//				mapper.insertSelective(new Employee(null,uid,"M",uid+"@guigu.com",1));
//		}
			/*List<Employee> selectByExampleWithDept = employeeMapper.selectByExampleWithDept(null);
			for(Employee e:selectByExampleWithDept){
				System.out.println(e.getEmpName()+"=="+e.getDepartment().getDeptName());
			}
			*/
			
			System.out.println("�������");
			
			
	
	
	}
}

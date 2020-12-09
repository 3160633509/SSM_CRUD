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
		//测试部门mapper
		/**
		 * 1.创建springIOC容器
		 * 2.从容器中拿
		 *	 ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
			DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
	
		 * 推荐spring的项目使用spring的单元测试，可以自动注入我们需要的组件
		 * 1.导入springTest组件
		 * 	2.@ContextConfiguration指定spring配置文件的位置
		 * 3.直接2Autowired要使用的组件即可
		 * 
		 * 
		 * 
		 * 
		 */
		

			System.out.println(departmentMapper);
			
			
			
			
			
			
			
			
			//1.插入幾個部門
//			departmentMapper.insertSelective(new Department(null,"開發部"));
//			departmentMapper.insertSelective(new Department(null,"測試部"));
			//2.生成員工數據，測試員工插入
			
//			employeeMapper.insertSelective(new Employee(null,"jenry","M","jenry@atguigu.com",1));
			//3.批量插入多個員工  批量  使用可以執行批量操作的sqlsession
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
			
			System.out.println("批量完成");
			
			
	
	
	}
}

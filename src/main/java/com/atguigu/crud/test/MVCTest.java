package com.atguigu.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.atguigu.crud.bean.Employee;
import com.github.pagehelper.PageInfo;


//使用spring測試模快提供的測試請求功能  测试crud请求的正确性
//spring4测试的时候需要servlet3.0的支持


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MVCTest {
	//传入springMvc的ioc
	@Autowired
	WebApplicationContext context;
	
	
	//虚拟mvc请求，获取到处理结果
		MockMvc mockmvc;
		
		@Before
		public void initMokcMvc(){
			mockmvc= MockMvcBuilders.webAppContextSetup(context).build();
		}
		
		
		
	@Test
	public void testPage() throws Exception{
		//模拟请求，拿到返回值
		MvcResult result= mockmvc.perform(MockMvcRequestBuilders.get("/emps").param("pn","1")).andReturn();
	
		//请求成功后  请求域中会有pageInfo  我们可以取出pageInfo进行验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo) request.getAttribute("pageinfo");
//		System.out.println("当前页码:"+pi.getPageNum());
//		System.out.println("总页码"+pi.getPages());
//		System.out.println("总记录数："+pi.getTotal());
//		//连续的那些页码
//		int[] nums= pi.getNavigatepageNums();
//		for(int i:nums){
//			System.out.println(" "+i);
//		}
		
		
		//获取员工数据
		List<Employee> list = pi.getList();
		//遍历员工数据
		for(Employee e:list){
			System.out.println(e.getClass());
			System.out.println("ID:"+e.getEmpId()+"==Name:"+e.getEmpName());
		}
		
		
	}
		
		
		
		
		
		

}

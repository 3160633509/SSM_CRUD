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


//ʹ��spring�yԇģ���ṩ�ĜyԇՈ����  ����crud�������ȷ��
//spring4���Ե�ʱ����Ҫservlet3.0��֧��


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MVCTest {
	//����springMvc��ioc
	@Autowired
	WebApplicationContext context;
	
	
	//����mvc���󣬻�ȡ��������
		MockMvc mockmvc;
		
		@Before
		public void initMokcMvc(){
			mockmvc= MockMvcBuilders.webAppContextSetup(context).build();
		}
		
		
		
	@Test
	public void testPage() throws Exception{
		//ģ�������õ�����ֵ
		MvcResult result= mockmvc.perform(MockMvcRequestBuilders.get("/emps").param("pn","1")).andReturn();
	
		//����ɹ���  �������л���pageInfo  ���ǿ���ȡ��pageInfo������֤
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo) request.getAttribute("pageinfo");
//		System.out.println("��ǰҳ��:"+pi.getPageNum());
//		System.out.println("��ҳ��"+pi.getPages());
//		System.out.println("�ܼ�¼����"+pi.getTotal());
//		//��������Щҳ��
//		int[] nums= pi.getNavigatepageNums();
//		for(int i:nums){
//			System.out.println(" "+i);
//		}
		
		
		//��ȡԱ������
		List<Employee> list = pi.getList();
		//����Ա������
		for(Employee e:list){
			System.out.println(e.getClass());
			System.out.println("ID:"+e.getEmpId()+"==Name:"+e.getEmpName());
		}
		
		
	}
		
		
		
		
		
		

}

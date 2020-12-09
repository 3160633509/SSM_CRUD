package com.atguigu.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/***
 * 
 * ����emp��crud
 *
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/***
	 * 
	 * ������������һ
	 * ����ɾ����  1-2-3
	 * ������1
	 * @param id
	 * @return
	 */
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmpById(@PathVariable("ids")String ids){
		//����- ˵��������ɾ��  �����ǵ���ɾ��
		//����ɾ��
		if(ids.contains("-")){
			List<Integer> del_ids=new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			
			//��װid�ļ���
			for(String id:str_ids){
				del_ids.add(Integer.parseInt(id));
			}
			employeeService.deleteBatch(del_ids);
		
		}else{
			//����ɾ��
			int id= Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		
		return Msg.success();
	}
	
	
	
	
	
	
	/***
	 * Ա������
	 * 1.֧��JSR303У��
	 * 2.����Hibernate-Validator
	 * 
	 * 
	 * 
	 */
	
	/***����Ա������
	 * 
	 * ���ֱ�ӷ���ajax=PUT�����󣬷�װ�����ݣ�����·���ϴ��Ĳ������ݣ���������ȫ��null
	 * 
	 * ���⣺�������������ݣ�����Employee��װ����
	 * update t_emp  where emp_id = 1014
	 * ԭ������tomcat������
	 * Tomcat
	 * 		1.���������е����ݣ���װһ��Map
	 * 		2.request.getParameter("empName")�ͻ�����map��ȡֵ
	 * 		3.��springmvc��װPOJO�����ʱ��
	 * 			 ���POJO��ÿ�����Ե�ֵ��request.getParameter("email")
	 * AJAX����PUT����������Ѫ����
	 * 		PUT�����������е����� request.getParamter("empName")�ò���
	 * 		����ԭ����Tomcatһ����PUT����Ͳ����װ�������е�����Ϊmap,��ֻ��POST��ʽ������ŷ�װ������Ϊmap
	 * org.apache.cataline.connector.Request-parseParameter
	 * if(!getConnector().isParseBodyMethod(getMethod())
	 * 
	 * ���������
	 * ����Ҫ��֧��ֱ�ӷ���PUT֮�������Ҫ��װ�������е�����
	 * ������HttpPutFormContentFilter ��
	 * �������þ��ǽ��������е����ݽ�����֤��һ��mapper,
	 * request�����°�װ��request.getParameter()����д���ͻ���Լ���װ��map��ȡ����
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg savaEmp(Employee employee){
		System.out.println("employee����"+employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	
	
	
	
	//����id��ѯԱ��
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id){
		Employee employee=employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	
	
	
	
	
	
	
	
	//У���û����Ƿ����
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName){
		//���ж��û����Ƿ��ǺϷ��ı��ʽ
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		 if(!empName.matches(regx)){
			 return Msg.fail().add("va_msg","�û���������6-16λ���ֺ���ĸ����ϻ���2-5λ����");
		 }
		 //���ݿ��û����ظ�У��
		boolean b=employeeService.checkUser(empName);
		if(b){
			return Msg.success();//100
		}else{
			return Msg.fail().add("va_msg", "�û���������");//200
		}
		
	}
	
	
	
	
	/***
	 * Ա������
	 * @return
	 */
	
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result){
		if(result.hasErrors()){
			//У��ʧ�ܣ�����ʧ��  ��ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
			Map<String,Object> map=new HashMap<String,Object>();
			List<FieldError>  errors = result.getFieldErrors();
			for(FieldError fieldError:errors){
				System.out.println("�����ֶ�����"+fieldError.getField());
				System.out.println("������Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields",map);
		}else{
		employeeService.saveEmployee(employee);
		return Msg.success();
		}
	}
	
	/***
	 * 
	 * ����jackson��
	 * 
	 * 
	 */
	//@ResponseBody  �����صĶ��󷵻�json�ַ���
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn){
		//�����퓲��
		//�{�÷�퓲��ֻ��Ҫ�{�� ����퓴a��ÿ퓵Ĵ�С
		PageHelper.startPage(pn,5);
		List<Employee> emps=employeeService.getAll();
		//ʹ��pageinfo���b��ԃ��ĽY��  ֻ��Ҫ��[ageinfo���o��������
		//pageinfo���b��Ԕ������Ϣ  �����҂���ԃ����Ĕ���  �����B�m�@ʾ��퓔�
		PageInfo page=new PageInfo(emps,5);
		
		return Msg.success().add("pageinfo",page);
		
	}
	
	
	
	
	/***
	 * ��ԃ�T����������퓣�
	 * @return
	 */
	
//	@RequestMapping("/emps")
//	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,
//			
//			Model model){
//		//�����퓲��
//		//�{�÷�퓲��ֻ��Ҫ�{�� ����퓴a��ÿ퓵Ĵ�С
//		PageHelper.startPage(pn,5);
//		List<Employee> emps=employeeService.getAll();
//		//ʹ��pageinfo���b��ԃ��ĽY��  ֻ��Ҫ��[ageinfo���o��������
//		//pageinfo���b��Ԕ������Ϣ  �����҂���ԃ����Ĕ���  �����B�m�@ʾ��퓔�
//		PageInfo page=new PageInfo(emps,5);
//		model.addAttribute("pageinfo",page);
//		return "list";
//	}

}

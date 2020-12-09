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
 * 处理emp的crud
 *
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/***
	 * 
	 * 单个批量二合一
	 * 批量删除：  1-2-3
	 * 单个：1
	 * @param id
	 * @return
	 */
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmpById(@PathVariable("ids")String ids){
		//包含- 说明是批量删除  否则是单个删除
		//批量删除
		if(ids.contains("-")){
			List<Integer> del_ids=new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			
			//组装id的集合
			for(String id:str_ids){
				del_ids.add(Integer.parseInt(id));
			}
			employeeService.deleteBatch(del_ids);
		
		}else{
			//单个删除
			int id= Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		
		return Msg.success();
	}
	
	
	
	
	
	
	/***
	 * 员工保存
	 * 1.支持JSR303校验
	 * 2.导入Hibernate-Validator
	 * 
	 * 
	 * 
	 */
	
	/***更新员工方法
	 * 
	 * 如果直接发送ajax=PUT的请求，封装的数据，除了路径上带的参数数据，其他数据全是null
	 * 
	 * 问题：请求体中有数据，但是Employee封装不上
	 * update t_emp  where emp_id = 1014
	 * 原因：这是tomcat的问题
	 * Tomcat
	 * 		1.将请求体中的数据，封装一个Map
	 * 		2.request.getParameter("empName")就会从这个map中取值
	 * 		3.而springmvc封装POJO对象的时候，
	 * 			 会把POJO中每个属性的值：request.getParameter("email")
	 * AJAX发送PUT请求引发的血案：
	 * 		PUT请求，请求体中的数据 request.getParamter("empName")拿不到
	 * 		根本原因是Tomcat一看是PUT请求就不会封装请求体中的数据为map,而只有POST形式的请求才封装请求体为map
	 * org.apache.cataline.connector.Request-parseParameter
	 * if(!getConnector().isParseBodyMethod(getMethod())
	 * 
	 * 解决方案：
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
	 * 配置上HttpPutFormContentFilter ；
	 * 它的作用就是讲请求体中的数据解析保证成一个mapper,
	 * request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg savaEmp(Employee employee){
		System.out.println("employee数据"+employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	
	
	
	
	//根据id查询员工
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id){
		Employee employee=employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	
	
	
	
	
	
	
	
	//校验用户名是否可用
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName){
		//先判断用户名是否是合法的表达式
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		 if(!empName.matches(regx)){
			 return Msg.fail().add("va_msg","用户名必须是6-16位数字和字母的组合或者2-5位中文");
		 }
		 //数据库用户名重复校验
		boolean b=employeeService.checkUser(empName);
		if(b){
			return Msg.success();//100
		}else{
			return Msg.fail().add("va_msg", "用户名不可用");//200
		}
		
	}
	
	
	
	
	/***
	 * 员工保存
	 * @return
	 */
	
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result){
		if(result.hasErrors()){
			//校验失败，返回失败  在模态框中显示校验失败的错误信息
			Map<String,Object> map=new HashMap<String,Object>();
			List<FieldError>  errors = result.getFieldErrors();
			for(FieldError fieldError:errors){
				System.out.println("错误字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
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
	 * 导入jackson包
	 * 
	 * 
	 */
	//@ResponseBody  将返回的对象返回json字符串
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn){
		//引入分頁插件
		//調用分頁插件只需要調用 傳入頁碼，每頁的大小
		PageHelper.startPage(pn,5);
		List<Employee> emps=employeeService.getAll();
		//使用pageinfo包裝查詢後的結果  只需要將[ageinfo交給頁面就行了
		//pageinfo封裝了詳細的信息  包括我們查詢出來的數據  傳入連續顯示的頁數
		PageInfo page=new PageInfo(emps,5);
		
		return Msg.success().add("pageinfo",page);
		
	}
	
	
	
	
	/***
	 * 查詢員工數據（分頁）
	 * @return
	 */
	
//	@RequestMapping("/emps")
//	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,
//			
//			Model model){
//		//引入分頁插件
//		//調用分頁插件只需要調用 傳入頁碼，每頁的大小
//		PageHelper.startPage(pn,5);
//		List<Employee> emps=employeeService.getAll();
//		//使用pageinfo包裝查詢後的結果  只需要將[ageinfo交給頁面就行了
//		//pageinfo封裝了詳細的信息  包括我們查詢出來的數據  傳入連續顯示的頁數
//		PageInfo page=new PageInfo(emps,5);
//		model.addAttribute("pageinfo",page);
//		return "list";
//	}

}

<!-- 使用js解析json 使用js通过dom增删改查改变页面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<!-- request.getContextPath()是拿到当前项目路径  它是以/开始的 -->
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<!-- web路径：
不以/开始的相对路径，找资源以当前资源的路径为基准，经常容易出问题
推荐  以/开始的路径，找资源 它是以服务器的根路径为标准的(http://localhost:3306)  需要加上项目名才能正确找到
	http:localhost:3306/crud

 -->


<!-- 引入jquery -->
<script type="text/javascript" src="${APP_PATH }/static/js/jquery.min.js"></script>
<!-- 引入样式 -->
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>

<!-- 员工修改的模态框 -->
<div class="modal fade" id="empUpdateModal"  tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
     <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">员工修改</h4>
      </div>
      <div class="modal-body">
      <form class="form-horizontal">
  <div class="form-group">
    <label class="col-sm-2 control-label">empName</label>
    <div class="col-sm-10">
     <p class="form-control-static"  id="empName_update_static" ></p>
      <span class="help-block"></span>
    </div>
  </div>
  
   <div class="form-group">
    <label class="col-sm-2 control-label">email</label>
    <div class="col-sm-10">
      <input type="text"  name="email" class="form-control" id="email_update_input" placeholder="email@atguigu.com">
      <span class="help-block"></span>
    </div>
  </div>
  
  
   
  
  <div class="form-group">
    <label class="col-sm-2 control-label">gender</label>
    <div class="col-sm-10">
  <label class="radio-inline">
	  <input type="radio" name="gender" id="gender1_update_input" value="m" checked="checked"> 男
	</label>
	<label class="radio-inline">
	  <input type="radio" name="gender" id="gender2_update_input" value="f"> 女
	</label>
	  </div>
  </div>
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label">deptName</label>
    <div class="col-sm-4">
			  <select class="form-control" name="dId">
			  	<!-- 部门提交部门id即可 -->
			  	
			</select>
    </div>
  </div>
  
  <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
      </div>
</form>


</div>
      	
    </div>
  </div>
</div>


















<!-- 员工添加的模态框 -->
<div class="modal fade" id="empAddModal"  tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
     <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">员工添加</h4>
      </div>
      <div class="modal-body">
      <form class="form-horizontal">
  <div class="form-group">
    <label class="col-sm-2 control-label">empName</label>
    <div class="col-sm-10">
      <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="empName">
      <span class="help-block"></span>
    </div>
  </div>
  
   <div class="form-group">
    <label class="col-sm-2 control-label">email</label>
    <div class="col-sm-10">
      <input type="text"  name="email" class="form-control" id="email_add_input" placeholder="email@atguigu.com">
      <span class="help-block"></span>
    </div>
  </div>
  
  
   
  
  <div class="form-group">
    <label class="col-sm-2 control-label">gender</label>
    <div class="col-sm-10">
  <label class="radio-inline">
	  <input type="radio" name="gender" id="gender1_add_input" value="m"> 男
	</label>
	<label class="radio-inline">
	  <input type="radio" name="gender" id="gender2_add_input" value="f"> 女
	</label>
	  </div>
  </div>
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label">deptName</label>
    <div class="col-sm-4">
			  <select class="form-control" name="dId">
			  	<!-- 部门提交部门id即可 -->
			  	
			</select>
    </div>
  </div>
  
  <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
      </div>
</form>


</div>
      	
    </div>
  </div>
</div>











		<!-- 搭建显示页面 -->
		<div class="container">
			<!-- 标题行 -->
			<div class="row">
				<div class="col-md121">
				<h1>SSM-CRUD</h1>
				</div>
				
			</div>
			
			
			
			
			<!-- 两个按钮  新增  删除 -->
			<div class="row">
			
 				 <div class="col-md-4 col-md-offset-8">
 				 	<button  type="button" class="btn btn-primary" id="emp_add_modal_btn">新增</button>
 				 	<button type="button" class="btn btn-danger" id="emp_delete_all">删除</button>
 				 </div>
			</div>
			
			
			
			<!-- 显示表格数据 -->
			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover" id="emps_tables">
					<thead>
									<tr>
									<th>
									
										<input type="checkbox" id="check_all">
									
									</th>
										<th>#</th>
										<th>empName</th>
										<th>gender</th>
										<th>email</th>
										<th>deptName</th>
										<th>操作</th>
									</tr>
						</thead>
						<tbody>
						
						
						
						
						</tbody>
					</table>
				</div>
			
			</div>
			
			
			
			<!-- 显示分页信息 -->
			<div class="row">
			<!-- 分页文字信息 -->
				<div class="col-md-6" id="page_info_area">
					<!-- 当前第页,
					总页，
					总共有条记录 -->
				</div>
				<!-- 分页条信息 -->
				<div class="col-md-6" id="page_nav_area">
					
		
		
		</div>
		 </div>
		 </div>
		 
		
		<script type="text/javascript">
			var totalRecord,currentPage;
			//1.页面加载完成以后，直接去发送一个ajax请求，要到分页数据
			$(function(){
				//去首页
				to_page(1);
						
			});
			
			
			
			
			
			
			
			//跳转到指定页码号
			function to_page(pn){
				$.ajax({
					url:"${APP_PATH }/emps",
					data:"pn="+pn,
					type:"GET",
					success:function(result){
						
						//1.解析页面员工信息
						build_emps_table(result);
					
						//2.解析并显示分页信息
						 bulid_page_info(result);
						
						//3.解析并显示分页条数据
						build_page_nav(result);
					}
				});
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		  function build_emps_table(result){
			  //构建前先清空
			  $("#emps_tables tbody").empty();
			 var emps=result.extend.pageinfo.list;
			 
			 $.each(emps,function(index,item){
				 var checkBoxId=$("<td><input type='checkbox' class='check_item'/></td>");
				 var empIdId=$("<td></td>").append(item.empId);
				 var empNameId=$("<td></td>").append(item.empName);
				 var genderId= $("<td></td>").append(item.gender=="m"?"男":"女");
				 var emailId=$("<td></td>").append(item.email);
				var deptNameId=$("<td></td>").append(item.department.deptName);
				var editBtn=$("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
							.append("编辑");
				//为编辑按钮添加一个自定义的属性来表示当前员工的id
				editBtn.attr("edit_id",item.empId);
				
				
				
				 var delBtn=$("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-trash"))
					.append("删除");
				 
				//为删除按钮添加一个自定义的属性来表示当前要删除的员工的id
					delBtn.attr("delete_id",item.empId);
				
				
				
				 var btnTd=$("<td></td>").append("  ").append(editBtn).append(delBtn);
				
				//append执行完成以后还是返回原来的元素
				$("<tr></tr>").append(checkBoxId)
							    .append(empIdId)
							   .append(empNameId)
								.append(genderId)
								.append(emailId)
								.append(deptNameId)
								.append(btnTd)
								.appendTo("#emps_tables tbody");
			 }); 
	  }
		
			//解析显示分页信息
			function bulid_page_info(result){
				//构建前先清空
				  $("#page_info_area").empty();
				$("#page_info_area").append("当前第"+result.extend.pageinfo.pageNum+" 页"+
						" 总共有"+result.extend.pageinfo.pages+" 页"+" 总"+result.extend.pageinfo.total+" 条记录");
				totalRecord=result.extend.pageinfo.total;
				currentPage=result.extend.pageinfo.pageNum;
			}
			
			
			//解析显示分页条
			function build_page_nav(result){
				//构建前先清空
				  $("#page_nav_area").empty();
				var ul=$("<ul></ul>").addClass("pagination");
				//构建元素
				var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
				var prePageLi=$("<li></li>").append($("<a></a>").append("&laquo;"));
				//判断是否有前一页
				if(result.extend.pageinfo.hasPreviousPage == false){
					firstPageLi.addClass("disabled");
					prePageLi.addClass("disabled");
				}else{
					//为元素添加点击翻页的事件
					firstPageLi.click(function(){
						to_page(1);
					});
					prePageLi.click(function(){
						to_page(result.extend.pageinfo.pageNum-1);
					});
					
				}
				
				
				var nextPageLi=$("<li></li>").append($("<a></a>").append("&raquo;"));
				var lastPageLi=$("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));
				
				//判断是否有下一页
				if(result.extend.pageinfo.hasNextPage == false){
					nextPageLi.addClass("disabled");
					lastPageLi.addClass("disabled");
				}else{

					//为元素添加点击翻页的事件
					nextPageLi.click(function(){
						to_page(result.extend.pageinfo.pageNum+1);
					});
					lastPageLi.click(function(){
						to_page(result.extend.pageinfo.pages);
					});
				}
				
				//添加首页和前一页的提示
				ul.append(firstPageLi).append(prePageLi);
				//1,2,3 。。。。遍历，给ul中添加页码提示
				$.each(result.extend.pageinfo.navigatepageNums,function(index,item){
					
					
					var numLi=$("<li></li>").append($("<a></a>").append(item));
					//判断当前页是否就是现在遍历的这一页
					if(result.extend.pageinfo.pageNum == item){
						numLi.addClass("active");
					}
					//给她绑定单击事件  去哪一页
					numLi.click(function(){
						to_page(item);
					});
					ul.append(numLi);
					
				});
				
				//添加下一页和末页的提示
				ul.append(nextPageLi).append(lastPageLi);
				//把ul加入到nav元素中
				var navEle=$("<nav></nav>").append(ul);
				//把navEle添加到div中
				navEle.appendTo("#page_nav_area");
			}
		
			
			//清空表单样式及内容
			function reset_form(ele){
				//重置表单数据内容
				$(ele)[0].reset();
				//重置表单样式
				$(ele).find("*").removeClass("has-error has-success");
				$(ele).find(".help-block").text("");
			}
			
			
			
			//点击新增按钮弹出模态框
			$("#emp_add_modal_btn").click(function(){
				// 表单完整重置：（表单数据以及表单样式）
				
				reset_form("#empAddModal form");
				
				
				//发送ajax请求，查出部门信息，显示在下拉列表中
				
				getDepts("#empAddModal select");
				
				
				//弹出模态框
				$("#empAddModal").modal({
					backdrop:"static"
				});
			});
			
			
			
			
			
			
			//查出部门信息，并显示在下拉列表
			function getDepts(ele){
				//情况之前下拉列表的值
				$(ele).empty();
				$.ajax({
					url:"${APP_PATH}/depts",
					type:"GET",
					success:function(result){
						console.log(result);
						//显示部门信息在下拉列表中
						//遍历部门信息
						$.each(result.extend.depts,function(){
							var optionEle=$("<option></option>").append(this.deptName).attr("value",this.deptId);
							optionEle.appendTo(ele);
							
						});
					}
					
				});
				
			}
			
			
			//把显示校验结果的提示信息取出一个方法
			function show_validate_msg(ele,status,msg){
				//清除当前元素的校验状态
				$(ele).parent().removeClass("has-success has-error");
				$(ele).next("span").text("");
				if("success"==status){
					$(ele).parent().addClass("has-success");
					$(ele).next("span").text("");
				}
				else{
					$(ele).parent().addClass("has-error");
					$(ele).next("span").text(msg);
				}
				
			}
			
		
			
			
			//校验表单数据
			function validate_add_form(){
				//1.拿到要校验的数据，使用正则表达式
				var empName=$("#empName_add_input").val();
				var regName=/(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
				if(!regName.test(empName)){
					//alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
					//每轮都应该清空元素之前的样式
					show_validate_msg("#empName_add_input","error","用户名可以是2-5位中文或者6-16位英文和数字的组合");
					
					return false;
				}else{
					show_validate_msg("#empName_add_input","success","");
				}
				//2.校验邮箱信息
				var email= $("#email_add_input").val();
				var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				if(!regEmail.test(email)){
					//alert("邮箱格式不正确");
					show_validate_msg("#email_add_input","error","邮箱格式不正确");
					
					return false;
				}else{
					show_validate_msg("#email_add_input","success","");
					
				}
				return true;;
			}
			
			
			
			
			$("#empName_add_input").change(function(){
				//校验用户名是否可用
				var empName=this.value;
				$.ajax({
					url:"${APP_PATH}/checkuser",
					data:"empName="+empName,
					type:"POST",
					success:function(result){
					 if(result.code==100){ 
							show_validate_msg("#empName_add_input","success","用户名可用");//100
							
							 $("#emp_save_btn").attr("ajax-va","success"); 
						  }else{
							//show_validate_msg("#empName_add_input","error",result.extend.va_msg); 
							 show_validate_msg("#empName_add_input","error","用户名不可用");
							  $("#emp_save_btn").attr("ajax-va","error"); 
						  }
						
					
					}
				});
			});
			
			
			
			
			//点击保存，保存员工
			$("#emp_save_btn").click(function(){
				//1.模态框中填写的表单数据提交给服务器进行保存
				//发送ajax请求之前要对提交给服务器的数据进行校验
				  if (!validate_add_form()){
					return false;
				}; 
				
				//1.判断之前的ajax用户名校验是否成功了，成功了才往下走
				   if($(this).attr("ajax-va")=="error"){
					return false;
				} ; 
				//2.发送ajax请求员工
				
				 $.ajax({
					 url:"emp",
					//url:"{APP_PATH}/emp",
					type:"POST",
					data:$("#empAddModal form").serialize(),
					success:function(result){
						//alert(result.msg);
						if(result.code==100){
						//当员工保存成功  
							//1.关闭模态框
							$("#empAddModal").modal("hide");
							//2.来到最后一页，显示刚才保存的数据
							//发送ajax请求显示最后一页数据即可
								to_page(totalRecord);
						}else{
							//显示失败信息
							
							//有哪个字段的错误信息，就显示哪个字段的
							if(undefined!=result.extend.errorFields.email){
								//显示邮箱错误信息
								show_validate_msg("#email_add_input","error",result.extend.errorFields.email);
								
							}
							if(undefined!=result.extend.errorFields.empName){
								//显示员工的错误信息
								show_validate_msg("#empName_add_input","error",result.extend.errorFields.empName);
							}
							
						}
							
					}
				 
				});
				
			});
			
			
			
			
			
			
			
			//按钮创建之前就绑定了click，所以绑不上
			//1）所以我们可以在创建按钮的时候去绑 2）绑定点击  live() 但是jquert新版本把这个方法删除了  ----->on()
			$(document).on("click",".edit_btn",function(){
				//0.查出部门信息并显示部门列表
					getDepts("#empUpdateModal select");
				//1.查出员工信息，显示员工信息
					getEmp($(this).attr("edit_id"));
					//2.把员工的id传递给模态框的更新按钮
					$("#emp_update_btn").attr("edit_id",$(this).attr("edit_id"));
					
					
					
				//3.弹出模态框
				//弹出模态框
				$("#empUpdateModal").modal({
					backdrop:"static"
				});
				
			});
			
			
			
			
			
			
			function getEmp(id){
				$.ajax({
					url:"${APP_PATH}/emp/"+id,
					type:"GET",
					success:function(result){
						var empDate=result.extend.emp;
						
						$("#empName_update_static").text(empDate.empName);
						$("#email_update_input").val(empDate.email);
						$("#empUpdateModal  input[name=gender]").val([empDate.gender]);
						$("#empUpdateModal select").val([empDate.dId]);
					
					}
					
				});
			}
			
			
			
			
			
			//点击更新  更新员工信息
			$("#emp_update_btn").click(function(){
				//1.验证邮箱是否合法
				//校验邮箱信息
				var email= $("#email_update_input").val();
				var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				if(!regEmail.test(email)){
					show_validate_msg("#email_add_input","error","邮箱格式不正确");
					return false;
				}else{
					show_validate_msg("#email_add_input","success","");
					
				}
				
				//2.发送ajax请求保存更新的员工数据
				$.ajax({
					url:"${APP_PATH}/emp/"+$(this).attr("edit_id"),
					/* type:"POST",
					data:$("#empUpdateModal form").serialize()+"&_method=PUT", */
					type:"PUT",
					data:$("#empUpdateModal form").serialize(),
					success:function(result){
						/* alert(result.msg); */
						//1.关闭对话框，
						$("#empUpdateModal").modal("hide"); 
						//2.回到本页面
						to_page(currentPage);
					}
					
				});
				
				
				
			});
				
			
			
			
			
					//单个删除
					$(document).on("click",".delete_btn",function(){
						//1.弹出是否确认删除对话框
						//alert($(this).parents("tr").find("td:eq(1)").text());
						var empName=$(this).parents("tr").find("td:eq(2)").text();
						var empId=$(this).attr("delete_id");
						if(confirm("确认删除["+empName+"]吗？")){
							//确认删除，发送ajax请求删除即可
							$.ajax({
								url:"${APP_PATH}/emp/"+empId,
								type:"DELETE",
								success:function(result){
									alert(result.msg);
									//回到本页
									to_page(currentPage);
								}
								
								
								
							});
						}
					});
			
			
			
					
					
					//完成全选和全不选
					$("#check_all").click(function(){
						//attr获取checked是underfined
						//我们这些dom元素的属性用property获取,推荐用attr获取自定义属性的值    用property获取原生属性的值
						//prop修改读取dom原生属性的值
						$(".check_item").prop("checked",$(this).prop("checked"));
					});
			
			
		
			
					
					//为check_item绑定事件
					$(document).on("click",".check_item",function(){
						//判断当前选择中的元素是不是5个      checked匹配所有被选中的复选框
						var flag=$(".check_item:checked").length==$(".check_item").length;
						$("#check_all").prop("checked",flag);
						
					});
					
					
					
					
					
					
					//点击全部删除，就批量删除
					$("#emp_delete_all").click(function(){
						var empNames="";
						var del_idstr="";
						$.each($(".check_item:checked"),function(){
							//要删除的每一个元素的姓名  组装员工名字的字符串
							empNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
							//组装员工id的字符串
							 del_idstr+=$(this).parents("tr").find("td:eq(1)").text()+"-";
						});
						//去除empNames多余的逗号
						empNames=empNames.substring(0, empNames.length-1);
						//去除 del_idstr多余的短横线
						 del_idstr= del_idstr.substring(0,del_idstr.length-1);
						if(confirm("确认删除【"+empNames+"】吗？")){
							//发送ajax请求去删除员工
							$.ajax({
								url:"${APP_PATH}/emp/"+del_idstr,
								type:"DELETE",
								success:function(result){
									alert(result.msg);
									//回到当前页面
									to_page(currentPage);
								}
							});
						}
					});
					
					
					
					
					
					
					
					
					
		</script>












</body>
</html>
 


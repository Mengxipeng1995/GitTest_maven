<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%
        int i=0;
        request.setAttribute("APP_PATH",request.getContextPath());
    %>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-2.2.1.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/Boostrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${APP_PATH}/static/Boostrap/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="h2">SSM CRUD</div>
    <div class="row">
        <div class="col-md-4 col-md-offset-10">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <br/>


    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped" id="tables">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
        <div class="pull-left" id="zongsu">

        </div>
        <div class="pull-right" id="page">

        </div>
    </div>


    </div>
<script type="text/javascript">

        go_page(1);


    function go_page(pageNumber){
        $.ajax({
            url:"${APP_PATH}/emp?pageNumber="+pageNumber,
            type:"get",
            success:function(data){
                build_emps_tables(data);
                build_emps_ul(data);
                build_emps_page(data);
            }
        });
    }


    function build_emps_tables(data) {
        $("#tables tbody").empty();
        var emps = data.map.pageInfo.list;
        //js遍历方法
        $.each(emps,function(index,item){
            var empId=$("<td></td>").append(item.empId);
            var empName = $("<td></td>").append(item.empName);
            var gender = $("<td></td>").append(item.gender=="M"?"男":"女");
            var email = $("<td></td>").append(item.email);
            var deptName = $("<td></td>").append(item.dept.deptName);
            var addButton =  $("<button></button>").addClass("btn btn-primary small").append("编辑");
            var deleteButton =  $("<button></button>").addClass("btn btn-danger small").append("删除");
            var button = $("<td></td>").append(addButton).append(" ").append(deleteButton);
            $("<tr></tr>").append(empId).append(empName).append(gender).append(email).append(email).append(deptName).append(button).appendTo("#tables tbody");
        });
    }
    //数据显示
    function build_emps_ul(data) {
        $("#zongsu").empty();
        $("#zongsu").append("一共"+data.map.pageInfo.pages+"页,"+"当前第"+ data.map.pageInfo.pageNum +"页,"+"共"+ data.map.pageInfo.total +"条数据");
    }
    //分页
    function build_emps_page(data){
        $("#page").empty();
        var ul =$("<ul></ul>").addClass("pagination");
       var per = $("<li></li>").append($("<a></a>").attr("href","#").append("&laquo;"));
       //判断是否有上一页
       if(data.map.pageInfo.hasPreviousPage == false){
           per=null;
       }
       //存在上一页添加点击上一页行为
        if(data.map.pageInfo.hasPreviousPage == true){
            per.click(function(){
                go_page(data.map.pageInfo.pageNum-1);
            })
        }

       var next = $("<li></li>").append($("<a></a>").attr("href","#").append("&raquo;"));

        //判断是否有下一页
        if(data.map.pageInfo.hasNextPage == false){
            next=null;
        }
        //存在下一页添加点击下一页行为
        if(data.map.pageInfo.hasNextPage == true){
            next.click(function(){
                go_page(data.map.pageInfo.pageNum+1);
            })
        }

       ul.append(per);
       $.each(data.map.pageInfo.navigatepageNums,function(index,item){

           var page =  $("<li></li>").append($("<a></a>").attr("href","#").append(item));
           if(data.map.pageInfo.pageNum == item){
               page.addClass("active");
           }
           //点击跳转
           page.click(function(){
               go_page(item);
           });

           ul.append(page);
       });
        ul.append(next);
       var nav = $("<nav></nav>").append(ul);
       nav.appendTo("#page");

    }
</script>
</body>
</html>

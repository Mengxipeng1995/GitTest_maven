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
            <table class="table table-striped">
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
               <c:forEach items="${pageInfo.list}" var="emp">
                <tr>
                    <th>${emp.empId}</th>
                    <th>${emp.empName}</th>
                    <th>${emp.gender=="M"?"男":"女"}</th>
                    <th>${emp.email}</th>
                    <th>${emp.dept.deptName}</th>
                    <th><button class="btn btn-primary small">编辑</button>
                        <button class="btn btn-danger small">删除</button>
                    </th>
                </tr>
               </c:forEach>
            </table>
        </div>
    </div>





        <div class="pull-right">
            <nav aria-label="Page navigation" >
                <ul class="pagination">

                    <c:if test="${!pageInfo.isFirstPage}">
                        <li>
                            <a href="/emp/?pageNumber=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>





                    <c:forEach items="${pageInfo.navigatepageNums}" var="pageNum">

                        <c:if test="${pageNum == pageInfo.pageNum}">
                            <li class="active"><a href="/emp/?pageNumber=${pageNum}">${pageNum}</a></li>
                        </c:if>

                        <c:if test="${pageNum != pageInfo.pageNum}">
                            <li><a href="/emp/?pageNumber=${pageNum}">${pageNum}</a></li>
                        </c:if>



                    </c:forEach>



                    <c:if test="${!pageInfo.isLastPage}">
                    <li>
                        <a href="/emp/?pageNumber=${pageInfo.pageNum+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    </c:if>

                </ul>
            </nav>
        </div>







    </div>

</body>
</html>

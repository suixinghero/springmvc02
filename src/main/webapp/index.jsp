<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/9/9
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript">
    $(function () {
       $("#btn").click(function () {
           // $.ajax({
           //     url:"user/testAjax",
           //     contentType:"application/json;charset=UTF-8",
           //     data:'{"username":"张三","password":"123","age":21}',
           //     type:"post",
           //     success:function(data){
           //         alert(data);
           //         alert(data.username);
           //     }
           // })
           $.post({
               url:"user/testAjax",
               data:{"username":"张三","password":"123","age":21},
               dataType:"json",
               success:function(result){
                   alert(result);
                   alert(result.age);
               }
           })
       });
    });
  </script>
</head>
<body>
  <a href="user/testString?username=张三">testString</a>
  <br>
  <a href="user/testVoid">testVoid</a>
  <br>
  <a href="user/testModelAndView">testModelAndView</a>
  <br>
  <a href="user/testForWardOrRedirect">testForWardOrRedirect</a>
  <br>

  <button id="btn">发送Ajax请求</button>
</body>
</html>

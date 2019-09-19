<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/9/16
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <a href="file/upload1">upload1</a>
<br>
  <form method="post" action="file/upload3" enctype="multipart/form-data">
      <table>
          <tr>
              <td>用户名:</td>
              <td><input type="text" name="name"/></td>
          </tr>
          <tr>
              <td>年龄:</td>
              <td><input type="text" name="age"/></td>
          </tr>
          <tr>
              <td>图片:</td>
              <td><input type="file" name="upload"/></td>
          </tr>
          <tr>
              <td colspan="2" align="center"><input type="submit" value="提交"/></td>
          </tr>
      </table>
  </form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: huang
  Date: 2019/1/13
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆-示范页面</title>
    <script type="text/javascript">
        var cookies = document.cookie.split(";");
        var userName, password, remember;
        for (var i = 0; i < cookies.length; i++)
        {
            var split = cookies[i].split("=");
            switch (split[0].trim()) {
                case "username":
                    userName = split[1];
                    break;
                case "remember":
                    remember = split[1];
                    break;
                case "password":
                    password = split[1];
                    break;
            }
        }
        window.onload = function () {
            console.log("entering onload function : " + userName + "," + password + "," + remember);
            if(userName !== undefined)
            {
                document.getElementById("username").value = userName;
            }
            if(remember==="true")
            {
                document.getElementById("password").value = password;
                document.getElementById("remember").checked = "checked";
            }
        }
    </script>
</head>
<body>
<form action="/login" method="post">
    <table border="1">
        <tr>
            <td>用户名：</td>
            <td><input id="username" type="text" name="userId"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input id="password" type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input id="remember" type="checkbox" name="remember">记住密码</td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登陆">
            </td>
        </tr>
    </table>
</form>
</body>
</html>

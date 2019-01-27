<%--
  Created by IntelliJ IDEA.
  User: huang
  Date: 2019/1/27
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JQuery import using test</title>
    <script type="text/javascript" src="./commonjs/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
       $(function () {
           $('#btn1').click(function(){
                var log = confirm("确定要这么做吗？");
                $("#sp1").text("you choose:" + log);
           });
           $("#btnPost").click(function () {
               var userName = $("#userName").val();
               var randomNum = $("#randomNum").val();
               var url = '${pageContext.request.contextPath}/AjaxTest'
               var params = {'userName':userName, 'randomNum':randomNum};

               $.post(url,params,function(data){
                    console.log(data);
                    var obj = JSON.parse(data);
                    $('#lbShowPostRet').text(obj.userName + " verified");
               },'text');
           });
       });
    </script>
</head>
<body>
<button id="btn1">测试confirm点击</button><br/>
<span id="sp1">预留text</span>
<hr/>
<p align="center">
    <form id="postForm" action="">
        <table border="1">
            <tr>
                <td colspan="2" align="center">post请求，返回一个json列表</td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="text" name="userName" id="userName"/>
                </td>
            </tr>
            <tr>
                <td>随机数：</td>
                <td>
                    <input type="text" name="randomNum" id="randomNum"/>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <input type="button" id="btnPost" value="发送请求">
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <label id="lbShowPostRet"> 点击按钮查看结果 </label>
                </td>
            </tr>
        </table>
    </form>
</p>
</body>
</html>

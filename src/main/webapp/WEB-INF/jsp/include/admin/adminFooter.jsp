<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<head>
    <style>
        .footer{
            width: 100%;
            height: 130px;
            background-color: #e0e0e0;
            font-size: 15px;
            text-align: center;
            position: absolute;        /* 相对于容器绝对定位 */
            bottom: 0px;
        }
    .github{
       text-decoration:none;
       color: #a94442;
        }
    .wj{
        padding-top:15px;
    }

    </style>
    <%
        String ctx = request.getContextPath();
    %>
</head>
<div class="footer">
    <img style="width: 15px;height: 15px;" src="<%=ctx%>/img/adminFooter/email.png">Email:1964007336@qq.com<br>
    <img style="width: 15px;height: 15px;" src="<%=ctx%>/img/adminFooter/address.png"><a class="github" href="https://github.com/Wj0801">Github:Wj0801</a><br>
    <p class="wj"> <img style="width: 15px;height: 15px;" src="<%=ctx%>/img/adminFooter/warning.png">Copyright © 2018.Company name.WangJun</p>
    <p class="wj" >皖ICP备18026057号</p>
</div  >

</html>
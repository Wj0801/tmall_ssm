<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<title>模仿天猫官网 ${p.name}</title>
<div class="categoryPictureInProductPageDiv">
    <img class="categoryPictureInProductPage" src="img/category/${p.category.id}.jpg">
</div>

<div class="productPageDiv">
    <!--单个图片信息-->
    <%@include file="imgAndInfo.jsp" %>
    <!--评价信息-->
    <%@include file="productReview.jsp" %>
    <!--详情图片-->
    <%@include file="productDetail.jsp" %>
</div>
<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF8">

    <link href="${ pageContext.request.contextPath }/assets/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${ pageContext.request.contextPath }/assets/css/style.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Happy+Monkey">


    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/jquery.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/bootstrap.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/f.js"></script>

    <title>${documentTitle}</title>
	<body style="font-family: Happy Monkey !important">
	
		 <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
          <ul id="user-menu" class="nav pull-right">
              

                        <li class="dropdown">
                            <a href="#" id="user-dd" role="button" class="dropdown-toggle menu-text" data-toggle="dropdown">
                              Sesion iniciada como:  ${fn:escapeXml(user.email)}
                            </a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="user-dd">
                                <li role="presentation">
                                    <a role="menuitem" href="${ pageContext.request.contextPath }/bin/admin/logout">Logout</a>
                                </li>
                            </ul>
                        </li>
                  
            </ul>
        </div>
    </div>

    <div class="container">
        <div id="main-logo">
            <div>
                <a href="${ pageContext.request.contextPath }/bin/admin/login"><img src="${ pageContext.request.contextPath }/assets/img/logo.PNG" /></a>
            	<div id="adminsitetitle"><span class="number muted"> Administrador del sitio </span></div>
            </div>
        </div>

        <hr />

        <div id="main">
            <c:choose>
                <c:when test="${sidebar}">
                    <div class="tabbable tabs-left">
                        <ul class="nav nav-tabs">
                            <c:if test="${tab_all}">
                                <c:set var="mclass" value="active" />
                            </c:if>
                            <li class="${mclass}"><a href="${ pageContext.request.contextPath }/bin/index/list?query=all">
                                <strong class="name text-warning">Todos los Productos</strong>
                                <br>
                                <c:if test="${numberOfProducts > 1}"><c:set var="j" value="s" /></c:if>
                                <span class="number muted">${numberOfProducts} producto${j}</span>
                            </a></li>

                            <c:forEach var="category" items="${categoriesList}">
                                <c:if test="${fn:length(category.products) gt 0}">
                                    <c:set var="mclass" value="" />
                                    <c:if test="${catid == category.id}">
                                        <c:set var="mclass" value="active" />
                                    </c:if>
                                    <li class="${mclass}">
                                        <a href="${ pageContext.request.contextPath }/bin/index/list?query=categories&id=${category.id}">
                                            <strong class="name">${fn:escapeXml(category.name)}</strong>
                                            <br>
                                            <c:if test="${fn:length(category.products) gt 1}"><c:set var="s" value="s" /></c:if>
                                            <span class="number muted">${fn:length(category.products)} producto${s}</span>
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active">
                 </c:when>
            </c:choose>

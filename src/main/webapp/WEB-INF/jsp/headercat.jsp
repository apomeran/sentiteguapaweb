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
	<link href="${ pageContext.request.contextPath }/themes/4/js-image-slider.css" rel="stylesheet" type="text/css" />
    <script src="${ pageContext.request.contextPath }/themes/4/js-image-slider.js" type="text/javascript"></script>
    <link href="${ pageContext.request.contextPath }/generic.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/jquery.js"></script>
   
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/assets/engine1/style.css" />
	<script type="text/javascript" src="${ pageContext.request.contextPath }/assets/engine1/jquery.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/f.js"></script>
    
    <title>${documentTitle}</title>
	<body style="font-family: Happy Monkey !important">
		
		<c:import url="../facebook/header.jsp" />
		
		
	<c:import url="../search.jsp" />

	
    <div class="container">
     		<c:import url="../top.jsp" />

		<div id="centralbanner" style="width:100%">
				<div id="sliderFrame">
						<div id="slider">
							<img src="${ pageContext.request.contextPath }/assets/slider/d.jpg" />
							<img src="${ pageContext.request.contextPath }/assets/slider/a.jpg" />
							<img src="${ pageContext.request.contextPath }/assets/slider/b.jpg" />
							<img src="${ pageContext.request.contextPath }/assets/slider/e.jpg" />
							<img src="${ pageContext.request.contextPath }/assets/slider/f.jpg" />
							<img src="${ pageContext.request.contextPath }/assets/slider/g.jpg" />
						</div>
						<!--Custom navigation buttons on both sides-->
						<div class="group1-Wrapper">
							<a onclick="imageSlider.previous()" class="group1-Prev"></a>
							<a onclick="imageSlider.next()" class="group1-Next"></a>
						</div>
						<!--nav bar-->
						<div style="text-align:center;padding:20px;z-index:20;">
							<a onclick="imageSlider.previous()" class="group2-Prev"></a>
							<a id='auto' onclick="switchAutoAdvance()"></a>
							<a onclick="imageSlider.next()" class="group2-Next"></a>
						</div>
				</div>
					<script type="text/javascript">
						//The following script is for the group 2 navigation buttons.
						function switchAutoAdvance() {
							imageSlider.switchAuto();
							switchPlayPauseClass();
						}
						function switchPlayPauseClass() {
							var auto = document.getElementById('auto');
							var isAutoPlay = imageSlider.getAuto();
							auto.className = isAutoPlay ? "group2-Pause" : "group2-Play";
							auto.title = isAutoPlay ? "Pause" : "Play";
						}
						switchPlayPauseClass();
					</script>
		</div>
        <div id="main">
            <c:choose>
                <c:when test="${sidebar}">
                    <div class="tabbable tabs-left">
                        <ul class="nav nav-tabs">
                            <c:if test="${tab_all}">
                                <c:set var="mclass" value="active" />
                            </c:if>
                            <li class="${mclass}"><a href="${ pageContext.request.contextPath }/bin/index/list">
                                <strong class="name text-warning">Todos las Categorias</strong>
                                <br>
                                <c:if test="${numberOfProducts > 1}"><c:set var="j" value="s" /></c:if>
                                <span class="number muted">${numberOfCategories} categoria${j}</span>
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
                            <br>
                            <c:import url="../facebook/likebox.jsp" />
                            
                            
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active">
                 </c:when>
            </c:choose>
	
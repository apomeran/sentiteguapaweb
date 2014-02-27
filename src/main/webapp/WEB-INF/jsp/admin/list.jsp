<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="../headerlogin.jsp" />

<%@ include file="../panelactions.jsp" %>

<hr>
   <c:choose>
	     <c:when test="${indexadmin}">
	     <div id="welcomeuser">
	     	<br><br>
	     	Bienvenido <strong>${user.email}<strong>
	     	<br><br>
	     </div>	
	     </c:when>
	     <c:otherwise>
						<br>
						<h1>${itemTitle}</h1>
						<br>
				    
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="font-size: 12px; text-transform: uppercase; color:green;">Administrador de ${itemTitle}<div style="float:right;">Acciones posibles </div></th>
						
					</tr>
				</thead>
			     <c:choose>
				     <c:when test="${indexadmin}">
				     </c:when>
				     <c:otherwise>
				     	<a href="${ pageContext.request.contextPath}/bin/admin/${linkAdmin}/add"><img class="panelicon" src="/s/assets/img/addicon.png"/> Crear  ${itemTitle}</a>
				     </c:otherwise>
				 </c:choose>
	 </c:otherwise>
	 </c:choose>
	<br><br>
	<tbody>
		<c:forEach var="item" items="${itemList}">
			<tr>
				<td><strong><c:out value="${item.name}"/> </strong>
				<c:choose>
					<c:when test="${showingColors or showingCategories}">
					<c:if test="${fn:length(item.products) gt 1}"><c:set var="s" value="s" /></c:if>
	                 <span class="number muted">${fn:length(item.products)} producto${s}</span>
					</c:when>
				</c:choose>
				<c:choose>
				  <c:when test="${showingProducts}">
				  - Categorias: 
				    <c:forEach var="cat" items="${item.categories}">
				       <strong><c:out value="${cat.name}  "/></strong> -
				    </c:forEach>  
				     Precio: <strong>$ <c:out value="${item.price}"/>.-</strong>
				  </c:when>
				</c:choose>
				<div style="float:right;"><a href="${ pageContext.request.contextPath}/bin/admin/${linkAdmin}/edit?id=${item.id}"> <img class="panelicon" src="/s/assets/img/editicon.png"/> Editar </a>
				<c:choose>
					<c:when test="${showingCategories or showingColors }">
					<c:if test="${fn:length(item.products) eq 0}">
					  <a href="${ pageContext.request.contextPath}/bin/admin/${linkAdmin}/delete?id=${item.id}"><img class="panelicon" src="/s/assets/img/deleteicon.png"/> Eliminar </a>  
					</c:if>
					</c:when>
					<c:otherwise>
					 <a href="${ pageContext.request.contextPath}/bin/admin/${linkAdmin}/delete?id=${item.id}"><img class="panelicon" src="/s/assets/img/deleteicon.png"/> Eliminar </a>  
					</c:otherwise>
				</c:choose>
				<c:choose>
				
					<c:when test="${showingCategories}">
						<c:if test="${item.numberPhotos gt 0}">
							  <a href="${ pageContext.request.contextPath}/bin/admin/${linkAdmin}/imagecategorylist?id=${item.id}"><img class="panelicon" src="/s/assets/img/imgicon.jpg"/> Ver Imagenes (${item.numberPhotos}) </a>  
						</c:if>
					</c:when>
					<c:when test="${showingColors}">
						<c:if test="${fn:length(item.colorimages) gt 0}">
							  <a href="${ pageContext.request.contextPath}/bin/admin/${linkAdmin}/imagecolorlist?id=${item.id}"><img class="panelicon" src="/s/assets/img/imgicon.jpg"/> Ver Imagenes (${fn:length(item.colorimages)}) </a>  
						</c:if>
					</c:when>
					<c:when test="${showingProducts}">
						<c:if test="${fn:length(item.prodimages) gt 0}">
							  <a href="${ pageContext.request.contextPath}/bin/admin/${linkAdmin}/imageprodlist?id=${item.id}"><img class="panelicon" src="/s/assets/img/imgicon.jpg"/> Ver Imagenes (${fn:length(item.prodimages)}) </a>  
						</c:if>
					</c:when>
				</c:choose>
				
				</div>
				</td>
			
			</tr>
			
		</c:forEach>
	</tbody>
	
</table>
   <c:choose>
	     <c:when test="${indexadmin}">
	     </c:when>
	     <c:otherwise>
	     	<a href="${ pageContext.request.contextPath}/bin/admin/${linkAdmin}/add"><img class="panelicon" src="/s/assets/img/addicon.png"/> Crear ${itemTitle} </a>
	     </c:otherwise>
	 </c:choose>	
<c:import url="../footer.jsp" />


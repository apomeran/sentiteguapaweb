<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href='http://fonts.googleapis.com/css?family=Akronim' rel='stylesheet' type='text/css'>

<c:import url="../headercat.jsp" />

<div class="restlist">
		
	<div id="titlecat" style="font-weight: bold; font-size: 28px; text-transform: uppercase;">Listado de Categorias</div>
      <div id="categorycontent" style="width: 717px; ">
	  <c:choose>
        <c:when test="${fn:length(categoryList) gt 0}">
        <hr>
           <c:forEach var="cat" items="${categoryList}">
           <c:if test="${fn:length(cat.products) gt 0}">
				<div class="single_category" style="width: 138px; height: 150px; margin-bottom: 20px;  margin-left: 3px; display:inline-block">
	     		 
					<a href="${ pageContext.request.contextPath }/bin/index/list?query=categories&id=${cat.id}">
						<c:choose>
    						<c:when test="${cat.catimages == 0}">
    							<img border="0" src="${ pageContext.request.contextPath }/assets/img/noimage.jpg" width="146" height="214">
    						</c:when>
    				    <c:otherwise>
							<img border="0" src="${ pageContext.request.contextPath }/webapp${cat.catimages}" width="146" height="214">
						</c:otherwise>
						</c:choose>
						<br>
						<br> <div style="font-size: 33px; float:center; font-family: Akronim !important"> ${cat.name} (${fn:length(cat.products)})</div>
					</a>

				</div>
			</c:if>	
			</c:forEach>
		</div>	
        </c:when>
        <c:otherwise>
            <p id="no_results" class="lead text-center">No hay categorias para mostrar</p>
        </c:otherwise>
    </c:choose>
</div>
<br><br><br>
<c:import url="../footer.jsp" />


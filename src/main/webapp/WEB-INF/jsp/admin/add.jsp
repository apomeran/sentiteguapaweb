<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="../headerlogin.jsp" />

<%@ include file="../panelactions.jsp" %>


<h1>Nuevo item en ${title} </h1>
<table class="table table-hover">
	<form:form class="loginForm form-horizontal" action="${ pageContext.request.contextPath }/bin/admin/${linkAdmin}/add" method="POST" commandName="${linkAdmin}Form">
	<form:errors path="*" cssClass="text-error" element="div" />
			
            <div class="loginUsername control-group">
                <label class="control-label">Nombre de ${title}:</label>
                <div class="controls">
                     <p>
                        <form:input path="name"  />
                    </p>
                </div>
            </div>
		            <c:choose>
						  <c:when test="${showingProductForm}">
		            		 <div class="loginUsername control-group">
		                		<label class="control-label">Precio:</label>
		              			  <div class="controls">
			                 		   <p>
			                   		     <form:input path="price" value="0" />
			                  		   </p>
		                          </div>
		                     </div>
		                     <div class="loginUsername control-group">
		                		<label class="control-label">Categorias:</label>
		              			  <div class="controls">
			                 		   <p>
			                 		   		<c:forEach items="${categoriesList}" var="cat">
			                 		   			${cat.name} <form:checkbox path="categories"  value="${cat.id}" />
			                 		   		</c:forEach>
			                  		   </p>
		                          </div>
		                     </div>
		                     
		                     <div class="loginUsername control-group">
		                		<label class="control-label">Talles Disponibles:</label>
		              			  <div class="controls">
			                 		   <p>
			                 		   		<c:forEach items="${sizesList}" var="size">
			                 		   			${size.name} (${size.code}) <form:checkbox path="sizes"  value="${size.id}" />
			                 		   		</c:forEach>
			                  		   </p>
		                          </div>
		                     </div>
		                     
		                      <div class="loginUsername control-group">
		                		<label class="control-label">Unisex:</label>
		              			  <div class="controls">
			                 		   <p>
			                 		   <select name="unisex" path="unisex" id="unisexList">
										    <option value="">Seleccionar</option>
										        <c:forEach items="${unisexList}" var="option">
										                <option value="${option}">
										                    <c:out value="${option}">
										                    </c:out>
										                </option>
										        </c:forEach>
										</select>
			                  		   </p>
		                          </div>
		                     </div>
		                      <div class="loginUsername control-group">
		                		<label class="control-label">Season:</label>
		              			  <div class="controls">
			                 		   <p>
			                 		    <select name="season" path="season" id="seasonList">
										    <option value="">Seleccionar</option>
										        <c:forEach items="${seasonList}" var="option">
										                <option value="${option}">
										                    <c:out value="${option}">
										                    </c:out>
										                </option>	
										        </c:forEach>
										</select>
			                  		   </p>
		                          </div>
		                     </div>
		                      <div class="loginUsername control-group">
		                		<label class="control-label">Stock:</label>
		              			  <div class="controls">
			                 		   <p>
			                   		     <form:input path="stock" value="" />
			                  		   </p>
		                          </div>
		                     </div>
		                      <div class="loginUsername control-group">
		                		<label class="control-label">Colores:</label>
		              			  <div class="controls">
			                 		   <p>
			                 		   		<c:forEach items="${colorsList}" var="color">
			                 		   			${color.name} <form:checkbox path="colors"  value="${color.id}" />
			                 		   		</c:forEach>
			                  		   </p>
		                          </div>
		                     </div>
		                  </c:when>
					</c:choose>   
            
                        <div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Crear ${title}">
            </div>

  </form:form>	
</table>


	
<c:import url="../footer.jsp" />


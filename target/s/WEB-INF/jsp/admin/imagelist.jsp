<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="../headerlogin.jsp" />

<%@ include file="../panelactions.jsp" %>


 <hr>
	<tbody>
		<c:forEach var="item" items="${itemList}">
			<tr>
				<td>URL:<strong> <c:out value="${item.url}"/></strong> - Nombre: <strong><c:out value="${item.name}"/> </strong>- Tamaño: <strong><c:out value="${item.size}"/></strong>
					<br> <br>
					<div>
						<img width=5% height:5% src="${ pageContext.request.contextPath}/webapp${item.url}${item.name}"></img>
						<div style=" float:right;">  
						  <a href="${ pageContext.request.contextPath}/bin/admin/${linkAdmin}/imagedelete?id=${item.id}&pid=${imgauxid}"><img class="panelicon" src="/sentite/assets/img/deleteicon.png"/> Eliminar </a>  
						</div>
					</div>
				<hr>	
				</td>
			</tr>
		</c:forEach>
	</tbody>
	
</table>
	
<c:import url="../footer.jsp" />


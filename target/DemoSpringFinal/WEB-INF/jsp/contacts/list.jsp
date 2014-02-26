<%@ include file="../headerlogin.jsp" %>

<%@ include file="../panelactions.jsp" %>

<table class="table table-hover">
	<thead>
		<tr>
			<th>Contactos</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="contact" items="${contacts}">
		<tr>
			<td><strong>Fecha: </strong>
			  <c:out value="${contact.contactDate}"/>
			</td>
			<td><strong>Contacto: </strong>
			  <c:out value="${contact.contactName}"/>
			</td>
			<td><strong>Email: </strong><c:out value="${contact.email}"/></td>
			<td><div><strong>Mensaje:</strong> <c:out value="${contact.message}"/></div></td>
			<td><a href="${ pageContext.request.contextPath}/bin/admin/contact/delete?id=${contact.id}"><img class="panelicon" style="width:20px" src="/sentite/assets/img/deleteicon.png"/></a> </td> 
			
		</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="../footer.jsp" %>
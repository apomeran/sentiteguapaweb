<%@ include file="../headerlogin.jsp" %>
<%@ include file="../panelactions.jsp" %>


<table class="table table-hover">
	<thead>
		<tr>
			<th>Ordenes</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="order" items="${orders}">
		<tr>
			<td>Fecha:
			  <c:out value="${order.orderDate}"/>
			</td>
			<td>Razon Social:
			  <c:out value="${order.customerName}"/>
			</td>
			<td>Direccion:<c:out value="${order.address}"/></td>
			<td>Total: <c:out value="$${order.total}.-"/></td>
			<td><a href='${ pageContext.request.contextPath }/bin/admin/order/view?id=${order.id}'>Ver Detalle</a></td>
			<td><a href="${ pageContext.request.contextPath}/bin/admin/order/delete?id=${order.id}"><img class="panelicon" style="width:20px" src="/s/assets/img/deleteicon.png"/></a> </td> 
			
		</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="../footer.jsp" %>
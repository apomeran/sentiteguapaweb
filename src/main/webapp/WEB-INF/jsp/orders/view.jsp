<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../headerlogin.jsp" />

<script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/maps.js"></script>
<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>

<%@ include file="../panelactions.jsp" %>

<div class="restdetail">
    <c:choose>
        <c:when test="${not empty order}">
			<br>
            <h2 class="restaurantTitle"> Pedido N� ${order.id}</h2>

            <dl class="dl-horizontal">
				<dt>Fecha:</dt>
                    <dd>${order.orderDate}</dd>
                <dt>Razon Social</dt>
                    <dd>${order.customerName}</dd>
                <dt>Total:</dt>
                    <dd>$${order.total}.-</dd>
             	<dt>Detalle:</dt>
                    
               	<c:forEach var="line" items="${order.orderLine}">
               	<div style="margin-bottom:10px">
				    <dd>Producto: ${line.product.name}</dd>
				    <dd>Precio: $${line.product.price} .-</dd>
				    <dd>Cantidad: ${line.quantity} unidad(es)</dd>
				    <dd>Color:</dd>
				    <c:forEach var="color" items="${line.product.colors}">
				       <dd>- ${color.name}</dd>
				    </c:forEach>
				    <dd>Talle: ${line.size} </dd>
				   <dd> -----</dd>
				</div>  
				  
				</c:forEach>
                <br>

            </dl>

            <br />

 
        </c:when>
        <c:otherwise>
            Disculpas, esta Orden no existe! 
        </c:otherwise>
    </c:choose>

    <hr />



<div>

<c:import url="../footer.jsp" />


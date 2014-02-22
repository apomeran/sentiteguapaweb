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
            <h2 class="restaurantTitle"> Pedido Nº ${order.id}</h2>

            <dl class="dl-horizontal">
				<dt>Fecha:</dt>
                    <dd>${order.orderDate}</dd>
                <dt>Razon Social</dt>
                    <dd>${order.customerName}</dd>
                <dt>Total:</dt>
                    <dd>$${order.total}0.-</dd>
             	<dt>Detalle:</dt>
                    
               	<c:forEach var="line" items="${order.orderLine}">
				
               	<div style="margin-bottom:10px">
			
				<dd>Linea ${line.id} </dd>
				<br>
				    <dd><strong>Producto: </strong>${line.product.name}	</dd>
				    <dd><strong>Precio: </strong>$${line.product.price} .-</dd>
				    <dd><strong>Cantidad:</strong> ${line.quantity} unidad(es)</dd>
				    <dd><strong>Color:</strong> - ${line.prodcolor.name} (ID ${line.prodcolor.id})</dd>
				    <dd><strong>Talle: </strong>${line.size} </dd>
				   <br>
				   <dd>-----</dd>
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


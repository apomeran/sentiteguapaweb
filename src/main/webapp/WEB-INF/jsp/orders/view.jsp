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
                <dt>Direccion:</dt>
                    <dd>${order.address} - ${order.state}  </dd>
                <dt>Telefono:</dt>
                    <dd>${order.phone}</dd>
                <dt>Email:</dt>
                    <dd>${order.email}</dd>
                <dt>Expreso:</dt>
                    <dd>${order.express}</dd>
                <dt>Iva:</dt>
                    <dd>${order.ivacondition}</dd>
                <dt>Cuit:</dt>
                    <dd>${order.cuit}</dd>    
                <br>
             	<dt>Detalle:</dt>
                    
               	<c:forEach var="line" items="${order.orderLine}">
				
               	<div style="margin-bottom:10px">
			
				<dd>Linea ID: ${line.id} </dd>
				<br>
					<dd>
					<img width="68" height="87" src="${ pageContext.request.contextPath }/webapp${line.product.prodimages[0].url}${line.product.prodimages[0].name}">
					</dd>
					
				    <dd><strong>Producto: </strong>${line.product.name}	</dd>
				    <dd><strong>Codigo Producto: </strong>${line.product.code}	</dd>
				    <dd><strong>Precio: </strong>$${line.product.price} .-</dd>
				    <dd><strong>Cantidad:</strong> ${line.quantity} unidad(es)</dd>
				    <dd><strong>Color:</strong> - ${line.prodcolor.name} (ID ${line.prodcolor.id}) </dd>
				    <dd>
					 <img width="40" height="40" src="${ pageContext.request.contextPath }/webapp${line.prodcolor.colorimages[0].url}${line.prodcolor.colorimages[0].name}">
					</dd>
					<br>
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


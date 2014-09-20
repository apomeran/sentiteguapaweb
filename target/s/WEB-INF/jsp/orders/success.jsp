<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../header.jsp" />
<div class='error'>
    <p>
    	Felicitaciones <strong>${order.customerName}</strong>! <br>
    	Su pedido fue realizado con exito.
    	<br>
    	Se ha enviado un email a <strong>${order.email} </strong>con los datos del envio.
    	<br>
		<br>
    	Recordamos que el total del pedido es <strong>$${order.total} </strong>.-
    	<br>
    	En breve un asesor comercial se estara comunicando con usted
    	    	
    </p>
    <br><br>
    <a href='${ pageContext.request.contextPath }/bin/index/list'>Volver al Inicio</a>
<div>
<c:import url="../footer.jsp" />


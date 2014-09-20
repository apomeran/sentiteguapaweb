<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../header.jsp" />
<div class='error'>
    <p>
    	Felicitaciones <strong>${contact.contactName}</strong>! <br>
    	Su contacto fue realizado con exito.
    	<br>
    	Se ha enviado un email a <strong>${contact.email} </strong>con un recordatorio del contacto.
    	<br>
    	En breve un asesor comercial se estara comunicando con usted
    	    	
    </p>
    <br><br>
    <a href='${ pageContext.request.contextPath }/bin/index/list'>Volver al Inicio</a>
<div>
<c:import url="../footer.jsp" />


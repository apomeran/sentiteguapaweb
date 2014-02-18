<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="headerlogin.jsp" />
<div class='error'>
    <p>
         El item no puede ser eliminado. Ya que depende de otras entidades
    </p>
    <a href='${ pageContext.request.contextPath }/bin/index/list'>Volver al Inicio</a>
<div>
<c:import url="footer.jsp" />


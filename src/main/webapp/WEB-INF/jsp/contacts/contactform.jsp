<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="../header.jsp" />

<h2 class="admin-title"> Formulario de Contacto </h2>

 
<table class="table table-hover">
	
	<form:form class="loginForm form-horizontal" action="${ pageContext.request.contextPath }/bin/index/contact" method="POST" commandName="contactForm">
	
	<form:errors path="*" cssClass="text-error" element="div" />
			
                        
            <div class="loginUsername control-group">
                <label class="control-label">*Nombre y Apellido:</label>
                <div class="controls">
                     <p>
                        <form:input path="contactName" />
                    </p>
                </div>
            </div>
            
            <div class="loginUsername control-group">
                <label class="control-label">*Telefono:</label>
                <div class="controls">
                     <p>
                        <form:input path="phone"/>
                    </p>
                </div>
            </div>
            
              <div class="loginUsername control-group">
                <label class="control-label">*Email:</label>
                <div class="controls">
                     <p>
                        <form:input path="email" />
                    </p>
                </div>
            </div>
            
               <div class="loginUsername control-group">
                <label class="control-label">Mensaje:</label>
                <div class="controls">
                     <p>
                        <form:textarea path="message" />
                    </p>
                </div>
            </div>
		                
            <div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Enviar Contacto">
            </div>

  </form:form>	
  
</table>

	
<c:import url="../footer.jsp" />


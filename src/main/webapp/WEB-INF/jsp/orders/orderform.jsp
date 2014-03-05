<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="../header.jsp" />

<h2 class="admin-title"> Formulario de Pedido </h2>

 
<table class="table table-hover">
	
	<form:form class="loginForm form-horizontal" action="${ pageContext.request.contextPath }/bin/index/order" method="POST" commandName="orderForm">
	
	<form:errors path="*" cssClass="text-error" element="div" />
			
          <div style="width: 500px">              
            <div class="loginUsername control-group" style="float:left">
                <label class="control-label">*Nombre y Apellido o Razon Social:</label>
                <div class="controls">
                     <p>
                        <form:input path="customerName" />
                    </p>
                </div>
            </div>
            
            <div class="loginUsername control-group" style="float:right">
                <label class="control-label">*Direccion:</label>
                <div class="controls">
                     <p>
                        <form:input path="address" />
                    </p>
                </div>
            </div>
           </div> 
          <div style="width: 500px">              
              <div class="loginUsername control-group" style="float:left">
                <label class="control-label">*Localidad:</label>
                <div class="controls">
                     <p>
                        <form:input path="city" />
                    </p>
                </div>
            </div>
            <div class="loginUsername control-group" style="float:right">
                <label class="control-label">*Provincia:</label>
                <div class="controls">
                     <p>
                        <form:input path="state" />
                    </p>
                </div>
            </div>
           </div> 
		   
            <div class="loginUsername control-group">
                <label class="control-label">*Telefono:</label>
                <div class="controls">
                     <p>
                        <form:input path="phone" />
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
                <label class="control-label">Cuit:</label>
                <div class="controls">
                     <p>
                        <form:input path="cuit" />
                    </p>
                </div>
            </div>
            
            <div class="loginUsername control-group">
                <label class="control-label">*Aporte:</label>
                <div class="controls">
                     <p>
	                 	  <select name="ivacondition" path="ivacondition" id="ivaconditionList">
	                 	    <option value="Monotributista">Monotributista</option>
	                 	    <option value="Inscripto">Inscripto</option>
	                 	  </select>
                    </p>
                </div>
            </div>
            
           <div class="loginUsername control-group">
                <label class="control-label">Expreso:</label>
                <div class="controls">
                     <p>
                        <form:input path="express" />
                    </p>
                </div>
            </div>
            <div class="loginUsername control-group">
                <label class="control-label">Pedido:</label>
                <div class="controls">
				<table border="1" width="100%" cellspacing="0" height="49">
					<tbody>
					<tr>
						<td width="30" >&nbsp;</td>
						<td width="502" >Articulo Nº</td>
						<td width="196" >Cantidad</td>
						<td class="Estilo2">Talle</td>
					</tr>
					<c:forEach begin="0" end="3" step="1" varStatus="loop">
						<tr>
						<td width="30" >${loop.count - 1}</td>
						<td width="502" class="Estilo2">
							 <p>
			                    <form:select path="orderLine[${loop.count - 1}].product">
			                       <form:options items="${prodList}"  itemLabel="name" itemValue="id" multiple="false"/>
			                    </form:select>
			                 </p>
						</td>
						<td>
			          		     <form:input path="orderLine[${loop.count - 1}].quantity" value="" />
						</td>
						<td>
						         <form:input path="orderLine[${loop.count - 1}].size" value="" />
						</td>
					</tr>	
					</c:forEach>
				</tbody>
			</table>
				
		     </div>
			 <br>
			<div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Enviar Pedido">
            </div>
            </div>
            
		                
         

  </form:form>	
</table>

	
<c:import url="../footer.jsp" />


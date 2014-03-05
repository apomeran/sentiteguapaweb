<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="../header.jsp" />

<h2 class="admin-title"> Emitir Pedido </h2> 
 
<table class="table table-hover">
	
	<form:form class="loginForm form-horizontal" action="${ pageContext.request.contextPath }/bin/index/checkout" method="POST" commandName="checkoutForm">
	
	<form:errors path="*" cssClass="text-error" element="div" />
		<div class="loginUsername control-group">
		<br>
                <label class="control-label"><strong>Detalle pedido:</strong></label>
                <div class="controls">
				<table border="1" width="100%" cellspacing="0" height="49">
					<tbody>
					<tr>
						<td width="30" >&nbsp;</td>
						<td width="302" >Articulo Nº</td>
						<td width="202" >Color</td>
						<td width="96" >Cantidad</td>
						<td width="100" >Talle</td>
						<td width="90">P. Unitario</td>
						<td width="90">Subtotal</td>
					</tr>
					
					<c:forEach var="line" items="${Order.orderLine}" varStatus="count">
						<tr>
						<td width="30" >${count.count}</td>
						<td width="502" class="Estilo2">
							 
			                    ${line.product.name} (Cod: ${line.product.code})
							</td>
						<td>
								 ${line.prodcolor.name} 
						</td>
						<td>
								 ${line.quantity}
						</td>
						<td>
					             ${line.size}
						</td>
						<td>
								 $${line.product.price}0.-
						</td>
						<td>
								 $${line.product.price * line.quantity}0.-
						</td>
					</tr>	
					</c:forEach>
				</tbody>
			</table>
			<div style="font-size:20px;margin-top:30px">
				El total de la orden es: <strong> $${Order.total}0.- </strong>
			</div>	
			<br>
		<div style="font-size:17px;margin-top:30px">
			<strong>Complete los siguientes datos:</strong>
		</div><br>
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
            
			<br><br>
			<div class="control-group controls">

                <input class="btn btn-info" type="submit" value="Enviar Pedido">
				
				<p><a href="${ pageContext.request.contextPath }/bin/index/killcheckout"> Vaciar Carrito </a> </p>

            </div>

		     </div>
	
            </div>
		                
            

  </form:form>	
  
</table>

	
<c:import url="../footer.jsp" />


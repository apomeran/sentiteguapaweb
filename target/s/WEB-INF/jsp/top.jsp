<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  
  <div id="main-logo">
            <div class="sentitelogo">
                <a href="${ pageContext.request.contextPath }/bin/index/list"><img src="${ pageContext.request.contextPath }/assets/img/logo2.png" /></a>
		   </div>

        </div>
      
        <hr />
		
		<div id="therealheader" style="width:100%; height:45px; font-size:25px; font-family: Happy Monkey !important;">
		
			<div style="margin-top:6px;">
		        <a id="prodbtn" style="margin-right:50px;margin-left:10px"  href="${ pageContext.request.contextPath }/bin/index/list">Inicio</a>
          	    <a id="pedbtn" style="margin-right:50px"href="${ pageContext.request.contextPath }/bin/index/order">Pedidos</a>
          	    <a id="contbtn" style="margin-right:50px" href="${ pageContext.request.contextPath }/bin/index/aboutus">Nosotros</a>
         	    <a id="contbtn" style="margin-right:0px" href="${ pageContext.request.contextPath }/bin/index/contact">Contacto</a>

				<div style="float:right; margin-top:-3px; font-size:20px">

				<a href="${ pageContext.request.contextPath }/bin/index/checkout"><button class="btn btn-primary" type="button"
														data-toggle="tooltip"
													data-original-title="Ver mas"><i class="icon-shopping-cart icon-white"></i> Carrito</button></a> ${fn:length(cartOrder.orderLine)} Item(s) - $${cartOrder.total}0.-
				</div>
			</div>
		</div>	
		<hr>
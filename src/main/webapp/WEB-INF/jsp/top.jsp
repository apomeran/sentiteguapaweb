<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  
  <div id="main-logo">
            <div class="sentitelogo">
                <a href="${ pageContext.request.contextPath }/bin/index/list"><img src="${ pageContext.request.contextPath }/assets/img/logo.PNG" /></a>
				<a href="https://www.facebook.com/sentiteguapa.mayoristamoda"><img id="facebookbtn" src="${ pageContext.request.contextPath }/assets/img/facebook_icon.png" /></a>
		   </div>

        </div>

        <p align="center">
	         <img src="${ pageContext.request.contextPath }/assets/img/ventapormayor.jpg" />
		</p>
        <hr />
		<div id="therealheader" style="width:100%; height:45px; font-size:25px; font-family: Happy Monkey !important;">
			<div style="margin-top:6px;">
		        <a id="prodbtn" style="font-color:white !important"  href="${ pageContext.request.contextPath }/bin/index/list">Inicio</a>
          	    <a id="pedbtn" style=""  href="${ pageContext.request.contextPath }/bin/index/order">Pedidos</a>
          	    <a id="contbtn" style=""  href="${ pageContext.request.contextPath }/bin/index/aboutus">Nosotros</a>
         	    <a id="contbtn" style=""  href="${ pageContext.request.contextPath }/bin/index/contact">Contacto</a>

				<div style="float:right; margin-top:2px; font-size:20px">

				<a href="${ pageContext.request.contextPath }/bin/index/checkout"><button class="btn btn-primary" type="button"
														data-toggle="tooltip"
													data-original-title="Ver mas"><i class="icon-shopping-cart icon-white"></i></button></a> ${fn:length(cartOrder.orderLine)} Item(s) - $${cartOrder.total}0.-
				</div>
			</div>
		</div>	
		<hr>
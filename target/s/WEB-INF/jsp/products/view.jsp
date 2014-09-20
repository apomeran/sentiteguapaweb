<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../header.jsp" />
<link href='http://fonts.googleapis.com/css?family=Akronim' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Over+the+Rainbow' rel='stylesheet' type='text/css'>

<script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/jquery.zoom.min.js"></script>
    <!-- Include jQuery. -->
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>

        <!-- Include Cloud Zoom CSS. -->
        <link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/cloudzoom.css" />

        <!-- Include Cloud Zoom script. -->
        <script type="text/javascript" src="${ pageContext.request.contextPath }/js/cloudzoom.js"></script>

        <!-- Call quick start function. -->
        <script type="text/javascript">
            CloudZoom.quickStart();
		</script>  




<div class="restdetail">
    <c:choose>
        <c:when test="${not empty product}">
						<c:forEach var="pr" items="${product.categories}">
						</c:forEach>

			<div style="margin-left:20px"><a href="${ pageContext.request.contextPath }/bin/index/list"> Inicio </a> > <a href="${ pageContext.request.contextPath }/bin/index/list"> Productos </a>> [<c:forEach var="pr" items="${product.categories}">
			                 <a href="${ pageContext.request.contextPath }/bin/index/list?query=categories&id=${pr.id}">${pr.name}</a>   
						</c:forEach>] > ${product.name}
			</div><br>			
			<div id="prod_container">
			
				<div id="prod_left" style="margin-left: 13px">
				  <c:if test="${not empty product.prodimages[0].url}">
						  <img class = "cloudzoom" src = "${ pageContext.request.contextPath }/webapp${product.prodimages[0].url}${product.prodimages[0].name}"
		             data-cloudzoom = "zoomImage: '${ pageContext.request.contextPath }/webapp${product.prodimages[0].url}${product.prodimages[0].name}'" />
		       			 <br/>
		       			 <br>
		        		 <c:forEach var="img" items="${product.prodimages}">
							<img width="68" height="87" class = 'cloudzoom-gallery' src ="${ pageContext.request.contextPath }/webapp${img.url}${img.name}" data-cloudzoom = "useZoom: '.cloudzoom', image: '${ pageContext.request.contextPath }/webapp${img.url}${img.name}', zoomImage: '${ pageContext.request.contextPath }/webapp${img.url}${img.name}' " >
						 </c:forEach>
        		  </c:if> 
        		  <c:if test="${empty product.prodimages[0].url}">
						  <img src="${ pageContext.request.contextPath}/assets/img/noimage.jpg"></img>
				  </c:if> 
				</div>
				<div id="prod_right">
					<br>
					<dl class="dl-horizontal" >
					
						
						<dt></dt>
							<dd style="font-size: 42px; width: 120%; line-height:0.9; margin-left:55px; ">${product.name}</dd>
							<br><br>
						<dt></dt>
							<dd class="pprodprice" style="font-size: 45px; margin-left:53px; margin-top:18px;  width: 700px; " >Precio: $${product.price}0.- </dd> 
							<br><br><br>

						<dt>Colores Disponibles</dt>
						<c:forEach var="color" items="${product.colors}">
						 
							<dd>
							  <div style="width: 500px;">
							   <div style="float:left;  font-size: 25px;  width:800px; margin-top:0px ; margin-bottom:30px"> - ${color.name} </div>
								<c:if test="${not empty color.colorimages[0].url}">
								<div style= "margin-left: 30px ; margin-top: 0px ; width: 50px; height: 50px; display:inline-block">
									<img width="30" height="30" src="${ pageContext.request.contextPath}/webapp${color.colorimages[0].url}${color.colorimages[0].name}"></img>
							 	</div>
								</c:if>
							  </div>	
						    </dd>
						</c:forEach>
						<br><br>
						<dt>Codigo Producto</dt>
							<dd>Art n° ${product.code}</dd>

						<dt>Temporada</dt>
							<dd>${product.season}</dd>
						<dt>Talles Disponibles</dt>
							<c:forEach var="size" items="${product.sizes}">
							<dd style="width: 500px">${size.name}(${size.code})</dd>
							</c:forEach>

					
						<br><br>
						<div id="cartbuttons" style="margin-left:68px !important">
						<div style="">
							<dd style="font-size: 40px; width: 500px; margin-left:-20px; ">Añadir al Carrito:</dd>

						</div>
						<br>
						Seleccionar color
						<select name="colors" id="colorid">
										        <c:forEach items="${product.colors}" var="color">
										                <option value="${color.id}">
										                    <c:out value="${color.name}">
										                    </c:out>
										                </option>	
										        </c:forEach>
						</select>
						<br>
						Seleccionar Talle
						<select name="sizes" id="sizeid">
										        <c:forEach items="${product.sizes}" var="size">
										                <option value="${size.id}">
										                    <c:out value="${size.name} - ${size.code}">
										                    </c:out>
										                </option>	
										        </c:forEach>
						</select>
						<BR>
						Seleccionar Cantidad
						<input type="number" id="quantity" name="quantity" min="1" max="2000" placeholder="Cantidad de productos" value="1"><br>
						<a id="cartcart" href="${ pageContext.request.contextPath }/bin/product/additem?id=${product.id}"><button class="btn btn-primary" type="button"
														data-toggle="tooltip"
														data-original-title="Añadir al carrito"><i class="icon-shopping-cart "></i> Agregar al carrito</button></a>
						
						
						</div>
					</dl>
					
				</div>
			</div>	
            
		

 
        </c:when>
        <c:otherwise>
            Disculpas, este Producto no existe! 
        </c:otherwise>
    </c:choose>
	<br><br><br><br><br><br><br><br>

    <hr>

<script type="text/javascript">
  document.getElementById("cartcart").onclick = function() {
  document.getElementById("cartcart").href+= "&quantity=" + document.getElementById("quantity").value + "&colorid=" + document.getElementById("colorid").value + "&sizeid=" + document.getElementById("sizeid").value; 
  window.location.replace(document.getElementById("cartcart").href);
    return false;

  };
</script>

<div>
<hr>
	<br><br><br><br><br><br><br><br>	<br><br><br><br><br><br><br><br>
<c:import url="../footer.jsp" />


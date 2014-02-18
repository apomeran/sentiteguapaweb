<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../header.jsp" />

 <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Tangerine&effect=brick-sign">



</style>
<div class="restlist" >
     <c:choose>
        <c:when test="${fn:length(productList) gt 0}">
		<div class="container" style="width:600px !important;">
		 <div class="well span8 offset2 " style="margin-left:-20px">
            <c:forEach var="product" items="${productList}" varStatus="count">
			<br>
							<div class="row-fluid user-row">
								<div class="span1">
									<img class="img-circle" style="max-width: 300%; margin-top: -30px;	margin-left: 410px;"
										src="${ pageContext.request.contextPath }/webapp${product.prodimages[0].url}${product.prodimages[0].name} "			
									alt="Product Picture">
								</div>
								<div class="span10">
									<a href="${ pageContext.request.contextPath }/bin/product/view?id=${product.id}">
									  <strong><div style="font-size: 49px; font-family: Tangerine">${product.name}</div></strong>
									 </a>
									<br>
									<span class="text-muted"> <div style="font-size: 34px; font-family: Tangerine; margin-left:25px">Precio: <strong>$${product.price}0.-</strong></div></span>
									<span class="text-muted"> <div style="font-size: 24px; font-family: Tangerine; margin-left:37px;margin-top:10px">-Temporada  <strong>${product.season} </strong> 2014-</div></span>

								</div>
								<div class="span1 dropdown-user" data-for=".${product.id}class">
									<i class="icon-chevron-down text-muted"></i>
								</div>
							</div>
						<br><br><hr>
						
							<div class="row-fluid user-infos ${product.id}class">
								<div class="span10 offset1">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<h3 class="panel-title" style="font-size:27px">${product.name}</h3>
										</div>
										<div class="panel-body">
											<div class="row-fluid">
												<div class="span3">
													<img class="img-circle" style="max-width: 154%;	"
										 src="${ pageContext.request.contextPath }/webapp${product.prodimages[0].url}${product.prodimages[0].name}"
										 alt="Product Picture">
												</div>
												<div class="span6" style="margin-left:100px">
													<table class="table table-condensed table-responsive table-user-information">
														<tbody>
														<tr>
															<td>Precio</td>
															<td>$${product.price}0.-</td>
														</tr>
														<tr>
															<td>Unisex</td>
															<td>${product.unisex}</td>
														</tr>
														<tr>
															<td>Talles Disponibles</td>
															<td><c:forEach var="size" items="${product.sizes}">
															   <dd>${size.code}</dd>
															</c:forEach>								
															</td>
														</tr>
														<tr>
															<td>Colores</td>
															<td><c:forEach var="color" items="${product.colors}">
															   <dd>${color.name}</dd>
															</c:forEach>								
															</td>
														</tr>
														<tr>
															<td>Categorias</td>
															<td><c:forEach var="cat" items="${product.categories}">
																	<c:set var="catid" value="${cat.id}" />
																    <dd><a href="${ pageContext.request.contextPath }/bin/index/list?query=categories&id=${cat.id}">${cat.name}</a></dd>
																</c:forEach>								
															</td>
														</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="panel-footer">
											<button class="btn  btn-primary" type="button" 	data-toggle="tooltip" data-original-title="Compartir"><i class="icon-share icon-white"></i></button>
											<span class="pull-right">
										
											
												<a href="${ pageContext.request.contextPath }/bin/product/view?id=${product.id}"><button class="btn btn-warning" type="button"
														data-toggle="tooltip"
														data-original-title="Ver mas"><i class="icon-edit icon-white"></i></button></a>
												
											</span>
											<br><br>
											<div style="width: 500px">
											<div style="display:inline-block">
												<div style="font-size:15px">Añadir al carrito: </div> <br>
												Color <select name="colors${count.count}" id="colorid${count.count}">
																		<c:forEach items="${product.colors}" var="color">
																				<option value="${color.id}">
																					<c:out value="${color.name}">
																					</c:out>
																				</option>	
																		</c:forEach>
												</select>
											</div>
											<div style="display:inline-block">
												Cantidad
												<input type="number" id="quantity${count.count}" name="quantity${count.count}" min="1" max="2000" placeholder="Cantidad de productos" value="1"><br>
											</div>
											<div style="display:inline-block">
												Talles Disponibles
												<select name="sizes${count.count}" id="sizeid${count.count}">
													<c:forEach items="${product.sizes}" var="size">
															<option value="${size.id}">
																<c:out value="${size.name} - ${size.code}">
																</c:out>
															</option>	
													</c:forEach>
												</select>
											</div>
											<div style="display:inline-block;">
											<a href="${ pageContext.request.contextPath }/bin/index/additem?id=${product.id}&catid=${catid}" id="cartcart${count.count}" onclick="cartAction(${count.count})">
												<button class="btn btn-primary" style="margin-top: -11px;" type="button"
															data-toggle="tooltip"
															data-original-title="Añadir al carrito"><i class="icon-shopping-cart "></i>
												</button>
											</a>
											</div>
											</div>
											<br><hr>
										</div>
									</div>
								</div>
							</div>


							
							
						
            </c:forEach>
				</div>
			</div>
        </c:when>
        <c:otherwise>
            <p id="no_results" class="lead text-center">No hay productos para mostrar</p>
        </c:otherwise>
    </c:choose>
</div>

<script type="text/javascript">
 function cartAction(i){
 alert(document.getElementById("cartcart" + i).href);
  document.getElementById("cartcart" + i).href += "&quantity=" + document.getElementById("quantity" + i).value + "&colorid=" + document.getElementById("colorid"+ i).value+ "&sizeid=" + document.getElementById("sizeid"+ i).value; 
   alert(document.getElementById("cartcart" + i).href);
  //window.location.replace(document.getElementById("cartcart" + i).href);
    return false;

 }
</script>

<c:import url="../footer.jsp" />


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../headercat.jsp" />
<link href='http://fonts.googleapis.com/css?family=Akronim' rel='stylesheet' type='text/css'>
	<div id="titlecat" style="font-weight: bold; font-size: 38px; text-transform: uppercase;"><a href="${ pageContext.request.contextPath }/bin/index/list?query=categories"><button style="width: 100%;" class="button btn-info">Ver todos los productos</button></a></div>
	<hr>
	<div id="titlecat" style="font-weight: bold; font-size: 28px; text-transform: uppercase;">Productos Destacados</div>

<div class="restlist">
		
      <div id="categorycontent" style="width: 717px; ">
	  <c:choose>
        <c:when test="${fn:length(productList) gt 0}">
           <c:forEach var="i" begin="0" end="7">
				<div class="single_category" style="width: 138px; height:180px; margin-bottom: 20px;  margin-left: 3px; display:inline-block">
		     		 <div>
		     		   <c:if test="${not empty productList[i].prodimages[0].url}">
								<img border="0" src="${ pageContext.request.contextPath }/webapp${productList[i].prodimages[0].url}${productList[i].prodimages[0].name}" width="146" height="214">
							  </c:if> 
		        			  <c:if test="${empty productList[i].prodimages[0].url}">
								  <img border="0"  width="146" height="214"  src="${ pageContext.request.contextPath}/assets/img/noimage.jpg"></img>
						 	  </c:if> 
						 	  <div style="font-size: 15px; margin-top:15px; text-align:center;"> Precio: $${productList[i].price}.- </div>
						
					</div>
					<a href="${ pageContext.request.contextPath }/bin/product/view?id=${productList[i].id}">
							<div style="font-size: 13px; text-align:center;"> ${fn:substring(productList[i].name,0,18)}        
							 <c:choose>
							 <c:when test="${fn:length(productList[i].name) gt 18}"> 
							 ..
							  </c:when>
				
					         </c:choose>
         </div>
					</a>	
				</div>
			</c:forEach>
		</div>	
        </c:when>
        <c:otherwise>
            <p id="no_results" class="lead text-center">No hay categorias para mostrar</p>
        </c:otherwise>
    </c:choose>
</div>
<br><br><br>
<div id="banner-modal" style="position: absolute;" class="modal fade" tabindex="-1">
		    <div class="modal-header" style="text-align: center;">
		        <h3 style="color: #32B96E !important">Sentite Guapa</h3>
		    </div>
		    <div class="modal-body style="text-align: center; max-height: 549px !important; ">
				<img src="${ pageContext.request.contextPath }/assets/slider/SentiteGuapa/L2.jpg" />

		    </div>
		    <div class="modal-footer" style="text-align: center;">
		        <button class="btn btn-link" data-dismiss="modal">Cerrar</button>
		    </div>
</div>
<script>
		 $( document ).ready(function() {
			   $('#banner-modal').modal('show');
		 });
</script>
<c:import url="../footer.jsp" />


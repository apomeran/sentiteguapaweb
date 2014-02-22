<script src="${ pageContext.request.contextPath }/js/jquery.1.9.1.min.js"></script>
<script src="${ pageContext.request.contextPath }/js/vendor/jquery.ui.widget.js"></script>
<script src="${ pageContext.request.contextPath }/js/jquery.iframe-transport.js"></script>
<script src="${ pageContext.request.contextPath }/js/jquery.fileupload.js"></script>
 
<!-- bootstrap just to have good looking page -->
<script src="${ pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
<link href="${ pageContext.request.contextPath }/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
 
<!-- we code these -->
<link href="${ pageContext.request.contextPath }/css/dropzone.css" type="text/css" rel="stylesheet" />
<script src="${ pageContext.request.contextPath }/js/myuploadfunction.js"></script>	

<h2>Subir Imagenes</h2>
<div style="width:500px;padding:20px">
   	Podes seleccionar las imagenes: 
	<br><br>
    <input id="fileupload" type="file" name="files[]" data-url="${ pageContext.request.contextPath }/bin/controller/upload${imageType}?id=${item.id}" multiple>
    <br><br>
	O podes probar: 
	<br><br>
	<div id="dropzone" style="font-size:20px; width:500px">Arrastrar fotos hasta aqui mismo</div>
	<br><br><br>
    <div id="progress">
        <div style="width: 0%;"></div>
    </div>
	<br><br>
	
	Datos de los archivos cargados:
	<br><br>
    <table id="uploaded-files" border="1" width="100%" cellspacing="0" height="49">
        <tr>
            <th>Nombre Archivo</th>
            <th>Peso Archivo</th>
            <th>Tipo Archivo</th>
        </tr>
    </table>
</div>
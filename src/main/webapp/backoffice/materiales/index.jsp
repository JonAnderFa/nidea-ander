<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>
<%@page import="com.ipartek.formacion.nidea.backoffice.controller.BackofficeMaterialesController"%>
    
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" >
    
<h1>Backofiice materiales</h1>
<% // Usamos EL => Expresion lenguage =>${} 
//Podemos usar cualquier expresion, si no hay expresion pinta la variable %>
<div class="row">
  <div class="col-sm-6">
		<form action="backoffice/materiales" method="get">
			<input type="hidden" name="op" value="<%=BackofficeMaterialesController.OP_BUSQUEDA%>">
			<input type="text" name="search" required placeholder="Nombre del Material"> 
			<input type="submit" value="Buscar"> <i class="fas fa-search"></i>
		</form>
	</div>
	<div class="col-sm-6">
		<form action="backoffice/materiales" method="get">
			<input type="hidden" name="op" value="<%=BackofficeMaterialesController.OP_MOSTRAR_FORMULARIO%>">
			<input type="submit" value="Agregar material">
		</form>
	</div>
</div>
 <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Data Table Example</div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
              <thead>
                <tr role="row"><th class="sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" style="width: 154px;">Nombre</th><th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 237px;">Precio</th><th rowspan="1" colspan="1">Edicion</th></tr>
              </thead>
              <tfoot>
                <tr><th rowspan="1" colspan="1">Nombre</th><th rowspan="1" colspan="1">Precio</th><th rowspan="1" colspan="1">Edicion</th></tr>
              </tfoot>
              <tbody> 
              <c:forEach items="${materiales}" var="material">
              	<tr role="row" class="odd">
              	
						<%String colorin="black";%>
						<c:choose>	
						<c:when test="${material.precio>6 && material.precio<=24}">
						<%colorin="blue";%>
						</c:when>
						<c:when test="${material.precio>24}">
						<%colorin="green"; %>	
						</c:when> 
						</c:choose>
						<td class="sorting_1"style=color:<%=colorin %>>${material.nombre.toUpperCase()}</td>
						<td style=color:<%=colorin %>>${material.precio} &euro;</td>
						<td><a href="backoffice/materiales?op=<%=BackofficeMaterialesController.OP_MOSTRAR_FORMULARIO%>&id=${material.id }"><i class="fas fa-trash"></i> / <i class="fas fa-pencil-alt"></i> </a></td>
					</tr>
			  </c:forEach>
               </tbody>
            </table>
         



<%@include file="/templates/footer.jsp" %>
<script src="https://code.jquery.com/jquery-1.12.4.js" ></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#dataTable').dataTable();
    });
</script>
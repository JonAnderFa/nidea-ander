<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>
<%@page import="com.ipartek.formacion.nidea.backoffice.controller.BackofficeRolesController"%>
 <h2>Detalle Rol</h2>
<div class="container">
	<div class="form-group row">
		<a class="btn btn-outline-dark btn-lg" href="backoffice/roles">Volver</a>
	</div>
	<form action="backoffice/roles" method="post">
	  <div class="form-group row">
	    <label for="idrol" class="col-sm-2 col-form-label">ID:</label>
	    <div class="col-sm-2">
	      <input type="text" class="form-control" name="idrol" readonly value="${rol.idrol}">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="nombre" class="col-sm-2 col-form-label">Rol</label>
	    <div class="col-sm-5">
	      <input type="text" value="${rol.nombre}" class="form-control" name="nombre" placeholder="Introduce el nombre del rol" >
	    </div>
	  </div>

	   
	  </div>
	</div>
	<br>  
	
		<c:if test="${rol.idrol == -1}">
		   <div class="form-group row">
			   <div class="col-sm-12">
			   	  <input type="hidrolden" name="op" value="<%=BackofficeRolesController.OP_GUARDAR%>"> 	
			      <button type="submit" class="btn btn-primary btn-lg btn-block">Crear</button>
			  </div>
		  </div>
		</c:if>
		  
		<c:if test="${rol.idrol > -1}">  
			  <div class="form-group row">
			    <div class="col-sm-6">
			      <input type="hidrolden" name="op" value="<%=BackofficeRolesController.OP_GUARDAR%>"> 	
			      <button type="submit" class="btn btn-success btn-lg btn-block">Modificar</button>
			    </div>
			    <div class="col-sm-6">
				    <button type="button" class="btn btn-danger btn-lg btn-block" data-toggle="modal" data-target="#exampleModal">
					 Eliminar
					</button>
					</div>
				  </div>
					
					<!-- Modal -->
					<div class="modal fade" idrol="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidrolden="true">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" idrol="exampleModalLabel">Eliminar Producto ${rol.idrol}</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidrolden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					        Esta seguro que quiere eliminar el producto: ${rol.nombre}? 
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				      
				      <a href="backoffice/roles?idrol=${rol.idrol}&op=<%=BackofficeRolesController.OP_ELIMINAR%>" 
				       class="btn btn-danger">Eliminar</a>
					      </div>
					    </div>
					  </div>
					</div>
				  
		</c:if>	  
	</form>
</div>


<%@include file="/templates/footer.jsp" %>
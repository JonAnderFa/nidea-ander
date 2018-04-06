
	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-dark border-bottom box-shadow">
	  
	  <a href="index.jsp">
	  	<img src="img/logo.png" class="logo" alt="Logo Nidea">
	  </a>	
            
      <nav class="my-2 my-md-0 mr-md-10">
        <a class="p-2 text-white" href="generar-mesa">Mesa</a>        
        <a class="p-2 text-white" href="calculadora"> Calculadora</a>     
        <a class="p-2 text-white" href="materiales"> Materiales</a>  
      </nav>
      <nav class=" text-right my-2 my-md-0 mr-md-10">
	      <c:if test="${empty usuario}">
	      	<a class=" btn btn-outline-primary" href="login">Login</a>
	      </c:if>
	      <c:if test="${!empty usuario}">
		      <a class=" badge badge-warning" href="backoffice/materiales?op=5" >${usuario}</a>
		      <a class="p-2 text-white" href="backoffice/materiales" > Materiales</a> 
		      <a class=" btn btn-outline-danger" href="logout">Logout</a>
	      </c:if>
      </nav>
      
     
    </div>
    
    <div class="container">
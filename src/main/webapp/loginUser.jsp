<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>

<div id="login">
<h2>Login Usuario</h2>

  <form class="form-signin" action="loginUser" method="post">     

      <div class="form-label-group">
        <input type="number" class="form-control"
               name="id" 
               placeholder="id" 
               required autofocus>
               
        <label for="id">Id</label>
      </div>

      <div class="form-label-group">
        <input type="test" 
               name="nombre" 
               class="form-control" 
               placeholder="nombre" required>
               
        <label for="nombre">Nombre</label>
      </div>
     
      <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
      
    </form>

</div>
<jsp:include page="templates/footer.jsp"></jsp:include>
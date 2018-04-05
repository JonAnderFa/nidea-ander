<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>
<h1>Materiales</h1>
<% // Usamos EL => Expresion lenguage =>${} 
//Podemos usar cualquier expresion, si no hay expresion pinta la variable %>


<ol>
<c:forEach items="${materiales}" var="material">
<%String colorin="black";%>
	<c:choose>	
	<c:when test="${material.precio>6 && material.precio<=24}">
		<%colorin="blue";%>
	</c:when>
	<c:when test="${material.precio>24}">
		<%colorin="green"; %>
		
	</c:when> 
	 
	
	</c:choose>
<li style=color:<%=colorin %>>${material.nombre} - ${material.precio} &euro;</li>
	
	
	
	
</c:forEach>
</ol>



<jsp:include page="/templates/footer.jsp"></jsp:include>
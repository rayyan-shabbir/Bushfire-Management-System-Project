<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Bushfire Management System </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Drones</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${drones != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${drones == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${drones != null}">
            			Edit Drones
            		</c:if>
						<c:if test="${drones == null}">
            			Add New Drones
            		</c:if>
					</h2>
				</caption>

				<c:if test="${drones != null}">
					<input type="hidden" name="id" value="<c:out value='${drones.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Drone Name</label> <input type="text"
						value="<c:out value='${drones.dname}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Drone Coordinates</label> <input type="text"
						value="<c:out value='${drones.dcoordinates}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>Drone Model</label> <input type="text"
						value="<c:out value='${drones.dmodel}' />" class="form-control"
						name="country">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
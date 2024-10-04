<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container mt-5">
	<h1 class="mb-4">Member List</h1>

	 
 

	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Role</th>
			  
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${members}">
				<tr>
					<td>${member.id}</td>
					<td>${member.firstName}</td>
					<td>${member.lastName}</td>
					<td>${member.email}</td>
					<td>${member.role}</td>
				 
					 
				</tr>
			</c:forEach>
		</tbody>
	</table>
 </div>
	 
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container mt-5">
	<h1 class="mb-4">Member List</h1>

	<button type="button" class="btn btn-primary mb-4"
		data-bs-toggle="modal" data-bs-target="#addMemberModal">Add
		New Member</button>

	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Role</th>
				<th>Squad</th>
				<th>Actions</th>
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
					<td>${member.squad.name}</td>
					<td><a href="members?action=get&id=${member.id}"
						class="btn btn-info btn-sm">View</a>
						<button type="button" class="btn btn-warning btn-sm"
							data-bs-toggle="modal" data-bs-target="#updateMemberModal"
							data-id="${member.id}" data-firstname="${member.firstName}"
							data-lastname="${member.lastName}" data-email="${member.email}"
							data-role="${member.role}" data-squadid="${member.squadId}">Edit</button>
						<form action="members" method="post" style="display: inline;">
							<input type="hidden" name="action" value="delete"> <input
								type="hidden" name="id" value="${member.id}">
							<button type="submit" class="btn btn-danger btn-sm"
								onclick="return confirm('Are you sure you want to delete this member?');">Delete</button>
						</form></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- Pagination Controls -->
	<div class="d-flex justify-content-center">
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<c:if test="${currentPage > 1}">
					<li class="page-item"><a class="page-link"
						href="members?action=list&page=${currentPage - 1}">Previous</a></li>
				</c:if>
				<c:forEach begin="1" end="${totalPages}" var="i">
					<li class="page-item ${currentPage == i ? 'active' : ''}"><a
						class="page-link" href="members?action=list&page=${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${currentPage < totalPages}">
					<li class="page-item"><a class="page-link"
						href="members?action=list&page=${currentPage + 1}">Next</a></li>
				</c:if>
			</ul>
		</nav>
	</div>


</div>



<!-- Form for adding -->
<div class="modal fade" id="addMemberModal" tabindex="-1"
	aria-labelledby="addMemberModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addMemberModalLabel">Add New Member</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form action="members" method="post">
				<input type="hidden" name="action" value="add">
				<div class="modal-body">
					<div class="mb-3">
						<label for="firstName" class="form-label">First Name</label> <input
							type="text" class="form-control" id="firstName" name="first_name"
							required>
					</div>
					<div class="mb-3">
						<label for="lastName" class="form-label">Last Name</label> <input
							type="text" class="form-control" id="lastName" name="last_name"
							required>
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">Email</label> <input
							type="email" class="form-control" id="email" name="email"
							required>
					</div>
					<div class="mb-3">
						<label for="role" class="form-label">Role</label> <select
							class="form-select" id="role" name="role" required>
							<c:forEach var="role" items="${roles}">
								<option value="${role}">${role}</option>
							</c:forEach>

						</select>

					</div>
					<div class="mb-3">
						<label for="squadId" class="form-label">Squad</label> <select
							class="form-select" id="squadId" name="squadId">
							<option value="" selected>Select a squad (optional)</option>
							<c:forEach var="squad" items="${squads}">
								<option value="${squad.id}">${squad.name}</option>
							</c:forEach>

						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Save Member</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- Form for Updating -->
<div class="modal fade" id="updateMemberModal" tabindex="-1"
	aria-labelledby="updateMemberModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="updateMemberModalLabel">Update
					Member</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form action="members" method="post">
				<input type="hidden" name="action" value="update"> <input
					type="hidden" id="updateMemberId" name="id" value="">
				<div class="modal-body">
					<div class="mb-3">
						<label for="updateFirstName" class="form-label">First Name</label>
						<input type="text" class="form-control" id="updateFirstName"
							name="first_name" required>
					</div>
					<div class="mb-3">
						<label for="updateLastName" class="form-label">Last Name</label> <input
							type="text" class="form-control" id="updateLastName"
							name="last_name" required>
					</div>
					<div class="mb-3">
						<label for="updateEmail" class="form-label">Email</label> <input
							type="email" class="form-control" id="updateEmail" name="email"
							required>
					</div>
					<div class="mb-3">
						<label for="updateRole" class="form-label">Role</label> <select
							class="form-select" id="updateRole" name="role" required>
							<c:forEach var="role" items="${roles}">
								<option value="${role}" ${role == member.role ? 'selected' : ''}>${role}</option>
							</c:forEach>
						</select>
					</div>
					<div class="mb-3">
						<label for="updateSquadId" class="form-label">Squad</label><select
							class="form-select" id="updateSquadId" name="squadId">
							<option value="" selected>Select a squad (optional)</option>
							<c:forEach var="squad" items="${squads}">
								<option value="${squad.id}"
									${squad.id == member.squadId ? 'selected' : ''}>${squad.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Update
						Member</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
const updateMemberModal = document.getElementById('updateMemberModal');
updateMemberModal.addEventListener('show.bs.modal', (event) => {
    const button = event.relatedTarget;
    
    // Extract member data from button attributes
    const id = button.getAttribute('data-id');
    const firstName = button.getAttribute('data-firstname');
    const lastName = button.getAttribute('data-lastname');
    const email = button.getAttribute('data-email');
    const role = button.getAttribute('data-role');
    const squadId = button.getAttribute('data-squadid');
    
    // Populate modal fields
    document.getElementById('updateMemberId').value = id;
    document.getElementById('updateFirstName').value = firstName;
    document.getElementById('updateLastName').value = lastName;
    document.getElementById('updateEmail').value = email;
    
    // Select the correct role in the dropdown
    const roleSelect = document.getElementById('updateRole');
    roleSelect.value = role; // Make sure this value matches the option value
    
    // Select the correct squad in the dropdown
    const squadSelect = document.getElementById('updateSquadId');
    squadSelect.value = squadId; // Make sure this value matches the option value
});

</script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container mt-5">
    <h1 class="mb-4">Squad List</h1>

    <button type="button" class="btn btn-primary mb-4" data-bs-toggle="modal" data-bs-target="#addSquadModal">Add New Squad</button>

    <c:if test="${not empty message}">
        <div class="alert alert-success" role="alert">${message}</div>
    </c:if>

    <c:if test="${not empty errors}">
        <div class="alert alert-danger" role="alert">
            <c:forEach var="error" items="${errors}">
                <p>${error}</p>
            </c:forEach>
        </div>
    </c:if>

    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Squad Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="squad" items="${squads}">
                <tr>
                    <td>${squad.id}</td>
                    <td>${squad.name}</td>
                    <td>
                       
                        <a href="squadMembers?action=listBySquad&id=${squad.id}" class="btn btn-success btn-sm">View Members</a>
                        <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#updateSquadModal" data-id="${squad.id}" data-name="${squad.name}">Edit</button>
                        <form action="squads" method="post" style="display: inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${squad.id}">
                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this squad?');">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Form for adding -->
<div class="modal fade" id="addSquadModal" tabindex="-1" aria-labelledby="addSquadModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addSquadModalLabel">Add New Squad</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="squads" method="post">
                <input type="hidden" name="action" value="add">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="squadName" class="form-label">Squad Name</label>
                        <input type="text" class="form-control" id="squadName" name="name" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save Squad</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Form for Updating -->
<div class="modal fade" id="updateSquadModal" tabindex="-1" aria-labelledby="updateSquadModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateSquadModalLabel">Update Squad</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="squads" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" id="updateSquadId" name="id" value="">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="updateSquadName" class="form-label">Squad Name</label>
                        <input type="text" class="form-control" id="updateSquadName" name="name" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Update Squad</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    const updateSquadModal = document.getElementById('updateSquadModal');
    updateSquadModal.addEventListener('show.bs.modal', (event) => {
        const button = event.relatedTarget; 
        const id = button.getAttribute('data-id'); 
        const name = button.getAttribute('data-name');

        const modalTitle = updateSquadModal.querySelector('.modal-title');
        const modalBodyInput = updateSquadModal.querySelector('#updateSquadName');
        const modalIdInput = updateSquadModal.querySelector('#updateSquadId');

        modalTitle.textContent = 'Update Squad ' + name;
        modalBodyInput.value = name;
        modalIdInput.value = id; 
    });
</script>

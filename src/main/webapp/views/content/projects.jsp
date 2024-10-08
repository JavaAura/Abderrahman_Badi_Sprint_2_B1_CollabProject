<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<div class="container mt-5">
    <h1>${title}</h1>
    <div class="projects-list">
        <div class="container mt-5">

        <c:if test="${not empty message}">
            <div class="alert alert-success" role="alert">
                ${message}
            </div>
        </c:if>

        <c:if test="${not empty errors}">
            <div class="alert alert-danger" role="alert">
                <c:forEach var="error" items="${errors}">
                    <p>${error}</p>
                </c:forEach>
            </div>
        </c:if>




        <div class="d-flex justify-content-end mb-3">
         <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addProjectModal">Add Project</button>
         </div>
     <form class="d-flex" action="" method="get">
         <input class="form-control me-2"  name="search" placeholder="Search" aria-label="Search">
         <button class="btn btn-outline-success" type="submit">Search</button>
     </form>



            <table class="table">
                <thead>
                    <tr>
                     <th>Name</th>
                     <th>Description</th>
                     <th>Start Date</th>
                     <th>End Date</th>
                     <th>Status</th>
                     <th>Management</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="project" items="${projects}">
                        <tr>
                           <td><a href="projects/tasks?project_id=${project.id}">${project.name}</a></td>
                           <td>${project.description}</td>
                           <td>${project.startDate}</td>
                           <td>${project.endDate}</td>
                           <td>${project.status}</td>

                            <td>
                                <div class="btn-group" role="group" aria-label="Gestion des projets">
                                 <button class="btn btn-primary me-2"
                                         data-bs-toggle="modal"
                                         data-bs-target="#updateModal"
                                         data-id="${project.id}"
                                         data-nom="${project.name}"
                                         data-description="${project.description}"
                                         data-dateDebut="${project.startDate}"
                                         data-dateFin="${project.endDate}">
                                     Update
                                 </button>

                                    <form action="" method="POST" style="display:inline;">
                                        <input type="hidden" name="id" value="${project.id}">
                                        <input type="hidden" name="action" value="delete" />
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>

                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<c:if test="${not empty projects}">
    <!-- Pagination Links -->
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <!-- Boucle pour afficher les numéros de page -->
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="page-item ${currentPage == i ? 'active' : ''}">
                    <a class="page-link" href="?page=${i}">${i}</a>
                </li>
            </c:forEach>

            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</c:if>



<!-- Bootstrap Modal for Update -->
<div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateModalLabel">Update Project</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="updateProjectForm" action="" method="post">
                    <input type="hidden" id="projectId" name="id" />
                    <input type="hidden" name="action" value="update" />
                    <div class="mb-3">
                        <label for="projectNom" class="form-label">Nom</label>
                        <input type="text" class="form-control" id="projectNom" name="nom" required />
                    </div>
                    <div class="mb-3">
                        <label for="projectDescription" class="form-label">Description</label>
                        <textarea class="form-control" id="projectDescription" name="description" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="projectDateDebut" class="form-label">Date de début</label>
                        <input type="date" class="form-control" id="projectDateDebut" name="dateDebut" required />
                    </div>
                    <div class="mb-3">
                        <label for="projectDateFin" class="form-label">Date de fin</label>
                        <input type="date" class="form-control" id="projectDateFin" name="dateFin" required />
                    </div>
                    <div class="mb-3">
                        <label for="projectStatut" class="form-label">Statut</label>
                         <select class="form-select" id="projectStatut" name="statut" required>
                                                    <option value="" disabled selected>Select a status</option>
                                                    <option value="PREPARATION">Preparation</option>
                                                    <option value="IN_PROGRESS">In Progress</option>
                                                    <option value="PAUSED">Paused</option>
                                                    <option value="COMPLETED">Completed</option>
                                                    <option value="CANCELED">Canceled</option>
                                                </select>
                    </div>

                    <div class="mb-3">
                        <label for="squadId" class="form-label">Squad</label>
                        <select name="squadId" class="form-select" id="squadId" required>
                            <option value="" disabled>Select a Squad</option>
                            <c:forEach var="squad" items="${squads}">
                                <option value="${squad.id}" ${project.squad.id == squad.id ? 'selected' : ''}>
                                    ${squad.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>


                    <button type="submit" class="btn btn-primary">Update</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap Modal for Add Project -->
<div class="modal fade" id="addProjectModal" tabindex="-1" aria-labelledby="addProjectModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addProjectModalLabel">Add Project</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="addProjectForm" action="" method="post">
                    <input type="hidden" name="action" value="add" />
                    <div class="mb-3">
                        <label for="projectNom" class="form-label">Nom</label>
                        <input type="text" class="form-control" id="projectNom" name="nom" required />
                    </div>
                    <div class="mb-3">
                        <label for="projectDescription" class="form-label">Description</label>
                        <textarea class="form-control" id="projectDescription" name="description" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="projectDateDebut" class="form-label">Date de début</label>
                        <input type="date" class="form-control" id="projectDateDebut" name="dateDebut" required />
                    </div>
                    <div class="mb-3">
                        <label for="projectDateFin" class="form-label">Date de fin</label>
                        <input type="date" class="form-control" id="projectDateFin" name="dateFin" required />
                    </div>
                    <div class="mb-3">
                        <label for="projectStatut" class="form-label">Statut</label>
                        <select class="form-select" id="projectStatut" name="statut" required>
                            <option value="" disabled selected>Select a status</option>
                            <option value="PREPARATION">Preparation</option>
                            <option value="IN_PROGRESS">In Progress</option>
                            <option value="PAUSED">Paused</option>
                            <option value="COMPLETED">Completed</option>
                            <option value="CANCELED">Canceled</option>
                        </select>
                    </div>

                              <div class="mb-3">
                                            <label for="squadId" class="form-label">Squad</label>
                                            <select name="squadId" class="form-select" id="squadId" required>
                                                <option value="" disabled selected>Select a Squad</option>
                                                <c:forEach var="squad" items="${squads}">
                                                    <option value="${squad.id}">${squad.name}</option> <!-- Corrected EL expressions -->
                                                </c:forEach>
                                            </select>
                                        </div>


                    <button type="submit" class="btn btn-primary">Add Project</button>
                </form>
            </div>
        </div>
    </div>
</div>





<script>

    document.addEventListener('DOMContentLoaded', function () {
        const updateModal = document.getElementById('updateModal');
        updateModal.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget; // Button that triggered the modal
            const id = button.getAttribute('data-id');
            const nom = button.getAttribute('data-nom');
            const description = button.getAttribute('data-description');
            const dateDebut = button.getAttribute('data-dateDebut');
            const dateFin = button.getAttribute('data-dateFin');
            const statut = button.getAttribute('data-statut');

            // Update the modal's content.
            const projectIdInput = document.getElementById('projectId');
            const projectNomInput = document.getElementById('projectNom');
            const projectDescriptionInput = document.getElementById('projectDescription');
            const projectDateDebutInput = document.getElementById('projectDateDebut');
            const projectDateFinInput = document.getElementById('projectDateFin');
            const projectStatutInput = document.getElementById('projectStatut');

            projectIdInput.value = id;
            projectNomInput.value = nom;
            projectDescriptionInput.value = description;
            projectDateDebutInput.value = dateDebut;
            projectDateFinInput.value = dateFin;
            projectStatutInput.value = statut;
        });
    });
</script>

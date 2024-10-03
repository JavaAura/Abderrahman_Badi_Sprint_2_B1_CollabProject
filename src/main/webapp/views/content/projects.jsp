<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-5">
    <h1>${title}</h1>
    <div class="projects-list">
        <div class="container mt-5">
            <table class="table">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Description</th>
                        <th>Date de début</th>
                        <th>Date de fin</th>
                        <th>Statut</th>
                        <th>Gestion</th> <!-- Nouvelle colonne pour la gestion -->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="project" items="${projects}">
                        <tr>
                            <td>${project.nom}</td>
                            <td>${project.description}</td>
                            <td>${project.dateDebut}</td>
                            <td>${project.dateFin}</td>
                            <td>${project.statut}</td>
                            <td>
                                <div class="btn-group" role="group" aria-label="Gestion des projets">
                                    <a href="updateProject?id=${project.id}" class="btn btn-primary me-2">Update</a>
                                    <a href="deleteProject?id=${project.id}" class="btn btn-danger">Delete</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

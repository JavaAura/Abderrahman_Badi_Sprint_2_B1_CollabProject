 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-5">
<h1>${title}</h1>
    <div class="projects-list">
        <c:forEach var="project" items="${projects}">
            <div class="project">
                <h2>${project.nom}</h2>
                <p>${project.description}</p>
                <p>Date de début: ${project.dateDebut}</p>
                <p>Date de fin: ${project.dateFin}</p>
                <p>Statut: ${project.statut}</p>
            </div>
        </c:forEach>
    </div>

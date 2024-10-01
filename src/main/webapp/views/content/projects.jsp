<h1>${title}</h1>
    <div class="projects-list">
        <c:forEach var="project" items="${projects}">
            <div class="project">
                <h2>${project.nom}</h2>
                <p>${project.description}</p>
                <p>Date de début: ${project.dateDebut}</p>
                <p>Date de fin: ${project.dateFin}</p>
                <p>Statut: ${project.statut}</p>
                <c:if test="${not empty project.taches}">
                    <h3>Tâches:</h3>
                    <ul>
                        <c:forEach var="task" items="${project.taches}">
                            <li>${task.title}</li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </c:forEach>
    </div>

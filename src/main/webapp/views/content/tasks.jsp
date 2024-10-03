 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>


 <div class="mx-auto pt-5 d-flex flex-column flex-xxl-row gap-1" style="width: 80%;">
     <div class="w-100 w-xxl-75 p-1 d-flex flex-column gap-5">
         <div>
             <c:if test="${project != null}">
                 <h1>${project.nom}</h1>
             </c:if>
         </div>
         <div class="d-flex w-100 gap-3">
             <div style="width: 33%;" class="d-flex flex-column gap-3 p-2 border rounded shadow task-container">
                 <p class="ps-2 fs-4 fw-medium">To Do</p>
                 <div class="d-flex flex-column shadow-sm min-vh-100 drop-zone" ondrop="dropHandler(event)"
                     ondragover="dragoverHandler(event)">
                     <div></div>
                     <div class="card cursor-pointer" id="card1" style="width: 18rem;">
                         <div class="card-body">
                             <h5 class="card-title">Card title 1</h5>
                             <h6 class="card-subtitle mb-2 text-body-secondary">Card subtitle</h6>
                             <p class="card-text">Some quick example text to build on the card title and make up the
                                 bulk of
                                 the card's content.</p>
                             <a href="#" class="card-link">Card link</a>
                             <a href="#" class="card-link">Another link</a>
                         </div>
                     </div>
                     <div></div>
                     <div class="card cursor-pointer" id="card2" style="width: 18rem;">
                         <div class="card-body">
                             <h5 class="card-title">Card title 2</h5>
                             <h6 class="card-subtitle mb-2 text-body-secondary">Card subtitle</h6>
                             <p class="card-text">Some quick example text to build on the card title and make up the
                                 bulk of
                                 the card's content.</p>
                             <a href="#" class="card-link">Card link</a>
                             <a href="#" class="card-link">Another link</a>
                         </div>
                     </div>
                 </div>

             </div>
             <div style="width: 33%;" class="d-flex flex-column gap-3 p-2 border rounded shadow task-container">
                 <p class="ps-2 fs-4 fw-medium">Doing</p>
                 <div class="d-flex flex-column shadow-sm min-vh-100 drop-zone" ondrop="dropHandler(event)"
                     ondragover="dragoverHandler(event)">
                     <div class="card cursor-pointer" id="card3" style="width: 18rem;">
                         <div class="card-body">
                             <h5 class="card-title">Card title 3</h5>
                             <h6 class="card-subtitle mb-2 text-body-secondary">Card subtitle</h6>
                             <p class="card-text">Some quick example text to build on the card title and make up the
                                 bulk of
                                 the card's content.</p>
                             <a href="#" class="card-link">Card link</a>
                             <a href="#" class="card-link">Another link</a>
                         </div>
                     </div>
                     <div class="card cursor-pointer" id="card4" style="width: 18rem;">
                         <div class="card-body">
                             <h5 class="card-title">Card title 4</h5>
                             <h6 class="card-subtitle mb-2 text-body-secondary">Card subtitle</h6>
                             <p class="card-text">Some quick example text to build on the card title and make up the
                                 bulk of
                                 the card's content.</p>
                             <a href="#" class="card-link">Card link</a>
                             <a href="#" class="card-link">Another link</a>
                         </div>
                     </div>
                 </div>
             </div>
             <div style="width: 33%;" class="d-flex flex-column gap-3 p-2 border rounded shadow task-container">
                 <p class="ps-2 fs-4 fw-medium">Done</p>
                 <div class="d-flex flex-column shadow-sm min-vh-100 drop-zone" ondrop="dropHandler(event)"
                     ondragover="dragoverHandler(event)">
                     <div class="card cursor-pointer" id="card5" style="width: 18rem;">
                         <div class="card-body">
                             <h5 class="card-title">Card title 5</h5>
                             <h6 class="card-subtitle mb-2 text-body-secondary">Card subtitle</h6>
                             <p class="card-text">Some quick example text to build on the card title and make up the
                                 bulk of
                                 the card's content.</p>
                             <a href="#" class="card-link">Card link</a>
                             <a href="#" class="card-link">Another link</a>
                         </div>
                     </div>
                     <div class="card cursor-pointer" id="card6" style="width: 18rem;">
                         <div class="card-body">
                             <h5 class="card-title">Card title 6</h5>
                             <h6 class="card-subtitle mb-2 text-body-secondary">Card subtitle</h6>
                             <p class="card-text">Some quick example text to build on the card title and make up the
                                 bulk of
                                 the card's content.</p>
                             <a href="#" class="card-link">Card link</a>
                             <a href="#" class="card-link">Another link</a>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
     </div>
     <div class="w-100 w-xxl-25 p-2 d-flex flex-column gap-2 pt-5 ps-3" style="font-size: 16px; font-weight: 500;">
         <c:if test="${project != null}">
             <p class="fw-medium">Project description : </p>
             <p>${project.description}</p>
             <div class="d-flex flex-column gap-1">
                 <p>Started at: ${project.dateDebut}</p>
                 <p>Deadline: ${project.dateFin}</p>
             </div>
             <p>Status:
                 <c:choose>
                     <c:when test="${project.statut == 'IN_PROGRESS'}">In Progress</c:when>
                     <c:when test="${project.statut == 'PREPARATION'}">Preparation</c:when>
                     <c:when test="${project.statut == 'PAUSED'}">Paused</c:when>
                     <c:when test="${project.statut == 'COMPLETED'}">Completed</c:when>
                     <c:when test="${project.statut == 'CANCELED'}">Canceled</c:when>
                 </c:choose>
             </p>
         </c:if>
     </div>
 </div>

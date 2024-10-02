 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>


 <div class="mx-auto pt-5 d-flex gap-1" style="width: 80%;">
     <div class="w-75 p-1 d-flex flex-column gap-5">
         <div>
             <c:if test="${project != null}">
                 <h1>${project.nom}</h1>
             </c:if>
         </div>
         <div class="d-flex w-100 gap-3">
             <div style="width: 33%;" class="d-flex gap-1 p-1 border rounded shadow">
                 <p class="ps-2 fs-4 fw-medium">To Do</p>
             </div>
             <div style="width: 33%;" class="d-flex gap-1 p-1 border rounded shadow">
                 <p class="ps-2 fs-4 fw-medium">Doing</p>
             </div>
             <div style="width: 33%;" class="d-flex gap-1 p-1 border rounded shadow">
                 <p class="ps-2 fs-4 fw-medium">Done</p>
             </div>
         </div>
     </div>
     <div class="w-25 p-2 d-flex flex-column gap-2 pt-5 ps-3" style="font-size: 16px; font-weight: 500;">
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

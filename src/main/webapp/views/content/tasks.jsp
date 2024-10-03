 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>


 <div class="mx-auto pt-5 d-flex flex-column flex-xxl-row gap-1" style="width: 80%;">
     <div class="w-100 w-xxl-75 p-1 d-flex flex-column gap-5">
         <div>
             <c:if test="${project != null}">
                 <h1>${project.name}</h1>
             </c:if>
         </div>
         <div class="d-flex w-100 gap-3">
             <div style="width: 33%;" class="d-flex flex-column gap-3 p-2 border rounded shadow task-container">
                 <p class="ps-2 fs-4 fw-medium">To Do</p>
                 <div class="d-flex flex-column min-vh-50 drop-zone" ondrop="dropHandler(event)"
                     ondragover="dragoverHandler(event)">
                     <c:forEach var="task" items="${todoTasks}">
                         <div class="card cursor-pointer shadow-sm" id="${task.id}" style="width: 18rem;">
                             <div class="card-body">
                                 <h5 class="card-title">${task.title}</h5>
                                 <h6 class="card-subtitle mb-2 text-body-secondary">Priority: ${task.taskPriority}</h6>
                                 <c:choose>
                                     <c:when test="${fn:length(task.description) > 99}">
                                         <p class="card-text">${fn:substring(task.description, 0, 99)}...</p>
                                     </c:when>
                                     <c:otherwise>
                                         <p class="card-text">${task.description}</p>
                                     </c:otherwise>
                                 </c:choose>
                                 <c:choose>
                                     <c:when test="${task.}">
                                         <!-- This is the 'if' block -->
                                         <p>Condition 1 is true!</p>
                                     </c:when>
                                     <c:otherwise>
                                         <!-- This is the 'else' block -->
                                         <p>Both conditions are false!</p>
                                     </c:otherwise>
                                 </c:choose>
                                 <a href="#" class="card-link">Card link</a>
                                 <a href="#" class="card-link">Another link</a>
                             </div>
                         </div>
                     </c:forEach>
                 </div>
                 <div class="d-flex flex-column w-100">
                     <button type="button" class="btn w-50 align-self-end" data-bs-toggle="modal"
                         data-bs-target="#exampleModal">
                         + Create Task
                     </button>
                 </div>
             </div>
             <div style="width: 33%;" class="d-flex flex-column gap-3 p-2 border rounded shadow task-container">
                 <p class="ps-2 fs-4 fw-medium">Doing</p>

                 <div class="d-flex flex-column min-vh-50 drop-zone" ondrop="dropHandler(event)"
                     ondragover="dragoverHandler(event)">
                     <c:forEach var="task" items="${doingTasks}">
                         <div class="card cursor-pointer shadow-sm" id="${task.id}" style="width: 18rem;">
                             <div class="card-body">
                                 <h5 class="card-title">${task.title}</h5>
                                 <h6 class="card-subtitle mb-2 text-body-secondary">Priority: ${task.taskPriority}</h6>
                                 <c:choose>
                                     <c:when test="${fn:length(task.description) > 99}">
                                         <p class="card-text">${fn:substring(task.description, 0, 99)}...</p>
                                     </c:when>
                                     <c:otherwise>
                                         <p class="card-text">${task.description}</p>
                                     </c:otherwise>
                                 </c:choose>
                                 <a href="#" class="card-link">Card link</a>
                                 <a href="#" class="card-link">Another link</a>
                             </div>
                         </div>
                     </c:forEach>
                 </div>
                 <div class="d-flex flex-column w-100">
                     <button type="button" class="btn w-50 align-self-end" data-bs-toggle="modal"
                         data-bs-target="#exampleModal">
                         + Create Task
                     </button>
                 </div>
             </div>
             <div style="width: 33%;" class="d-flex flex-column gap-3 p-2 border rounded shadow task-container">
                 <p class="ps-2 fs-4 fw-medium">Done</p>
                 <div class="d-flex flex-column min-vh-50 drop-zone" ondrop="dropHandler(event)"
                     ondragover="dragoverHandler(event)">
                     <c:forEach var="task" items="${doneTasks}">
                         <div class="card cursor-pointer shadow-sm" id="${task.id}" style="width: 18rem;">
                             <div class="card-body">
                                 <h5 class="card-title">${task.title}</h5>
                                 <h6 class="card-subtitle mb-2 text-body-secondary">Priority: ${task.taskPriority}</h6>
                                 <c:choose>
                                     <c:when test="${fn:length(task.description) > 99}">
                                         <p class="card-text">${fn:substring(task.description, 0, 99)}...</p>
                                     </c:when>
                                     <c:otherwise>
                                         <p class="card-text">${task.description}</p>
                                     </c:otherwise>
                                 </c:choose>
                                 <a href="#" class="card-link">Card link</a>
                                 <a href="#" class="card-link">Another link</a>
                             </div>
                         </div>
                     </c:forEach>
                 </div>
                 <div class="d-flex flex-column w-100">
                     <button type="button" class="btn w-50 align-self-end" data-bs-toggle="modal"
                         data-bs-target="#exampleModal">
                         + Create Task
                     </button>
                 </div>
             </div>
         </div>
     </div>
     <div class="w-100 w-xxl-25 p-2 d-flex flex-column gap-2 pt-5 ps-3" style="font-size: 16px; font-weight: 500;">
         <c:if test="${project != null}">
             <p class="fw-medium mt-5 fs-3">Project description : </p>
             <p>${project.description}</p>
             <div class="d-flex flex-column gap-1">
                 <p>Started at: ${project.startDate}</p>
                 <p>Deadline: ${project.endDate}</p>
             </div>
             <p>Status:
                 <c:choose>
                     <c:when test="${project.status == 'IN_PROGRESS'}">In Progress</c:when>
                     <c:when test="${project.status == 'PREPARATION'}">Preparation</c:when>
                     <c:when test="${project.status == 'PAUSED'}">Paused</c:when>
                     <c:when test="${project.status == 'COMPLETED'}">Completed</c:when>
                     <c:when test="${project.status == 'CANCELED'}">Canceled</c:when>
                 </c:choose>
             </p>
         </c:if>
     </div>
 </div>

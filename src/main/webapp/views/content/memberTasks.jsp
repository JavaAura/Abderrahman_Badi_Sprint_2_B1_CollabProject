<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Task List</title>
</head>
<body>
    <h1>Tasks for Project: ${project.name}</h1>

    <c:if test="${not empty todoTasks}">
        <h2>To Do</h2>
        <ul>
            <c:forEach var="task" items="${todoTasks}">
                <li>
                    ${task.title} - ${task.description}
                    <span>(Priority: ${task.taskPriority})</span>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${not empty doingTasks}">
        <h2>Doing</h2>
        <ul>
            <c:forEach var="task" items="${doingTasks}">
                <li>
                    ${task.title} - ${task.description}
                    <span>(Priority: ${task.taskPriority})</span>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${not empty doneTasks}">
        <h2>Done</h2>
        <ul>
            <c:forEach var="task" items="${doneTasks}">
                <li>
                    ${task.title} - ${task.description}
                    <span>(Priority: ${task.taskPriority})</span>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty todoTasks and empty doingTasks and empty doneTasks}">
        <p>No tasks available for this project.</p>
    </c:if>
</body>
</html>

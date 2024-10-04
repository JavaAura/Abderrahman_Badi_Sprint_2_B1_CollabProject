<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!-- Modal -->
<div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="taskModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-fullscreen-sm-down">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Update Task</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body position-relative">
                <form id="taskForm" action="" method="post"
                    class="d-flex flex-column flex-lg-row gap-5 items-center">
                    <div class="d-flex flex-column gap-3 w-100 w-lg-66">
                        <input type="hidden" name="action" value="update" />
                        <input type="hidden" id="updatedTaskId" name="taskId">
                        <div class="form-group">
                            <label for="taskTitle">Title</label>
                            <input type="text" class="form-control" id="updatedTaskTitle" name="taskTitle" required>
                        </div>
                        <div class="form-group">
                            <label for="taskDescription">Description</label>
                            <textarea class="form-control" id="updatedTaskDescription" name="taskDescription" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="taskPriority">Priority</label>
                            <select class="form-control" id="updatedTaskPriority" name="taskPriority">
                                <option value="LOW">Low</option>
                                <option value="MEDIUM">Medium</option>
                                <option value="HIGH">High</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary">Update Task</button>
                    </div>
                    <div class="w-100 w-lg-33 pt-2 pb-10">
                        <div class="form-group">
                            <label for="squadMember">Assigned to</label>
                            <select class="form-control" id="updatedSquadMember" name="squadMember">
                                <option value="" selected disabled hidden>No member is assigned yet</option>
                                <c:forEach var="member" items="${members}">
                                    <option value="${member.id}">${member.firstName} ${member.lastName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <p id="assignDate"></p>
                    </div>
                </form>
                <form action="" method="post" class="position-absolute bottom end">
                    <input type="hidden" id="deletedTaskId" name="taskId">
                    <input type="hidden" name="action" value="delete" />
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

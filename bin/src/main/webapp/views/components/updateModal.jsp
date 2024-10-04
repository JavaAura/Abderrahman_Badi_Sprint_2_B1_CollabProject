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
            <div class="modal-body">
                <form id="taskForm" class="d-flex flex-column gap-3">
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
                </form>
            </div>
        </div>
    </div>
</div>

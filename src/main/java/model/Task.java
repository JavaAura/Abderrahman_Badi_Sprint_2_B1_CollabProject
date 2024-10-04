package model;

import java.time.LocalDateTime;

import model.enums.TaskPriority;
import model.enums.TaskStatus;

public class Task {

    private long id;
    private String title;
    private String description;
    private TaskPriority taskPriority;
    private TaskStatus taskStatus;
    private Project project;
    private Member member;
    private LocalDateTime assignDate;

    public Task() {

    }

    public Task(String title, String description, TaskPriority taskPriority, TaskStatus taskStatus,
            Project project) {
        this.title = title;
        this.description = description;
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
        this.project = project;
    }

    public Task(long id, String title, String description, TaskPriority taskPriority, TaskStatus taskStatus,
            Project project) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
        this.project = project;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public TaskPriority getTaskPriority() {
        return this.taskPriority;
    }

    public void setTaskPriority(TaskPriority value) {
        this.taskPriority = value;
    }

    public TaskStatus getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(TaskStatus value) {
        this.taskStatus = value;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project value) {
        this.project = value;
    }

    public Member getMember() {
      return this.member;
    }
    public void setMember(Member value) {
      this.member = value;
    }

    public LocalDateTime getAssignDate() {
      return this.assignDate;
    }
    public void setAssignDate(LocalDateTime value) {
      this.assignDate = value;
    }
}

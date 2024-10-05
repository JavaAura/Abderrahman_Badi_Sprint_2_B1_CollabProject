package service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Project;
import model.Task;
import model.enums.TaskPriority;
import model.enums.TaskStatus;

public class TaskServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(TaskServiceTest.class);

	private TaskService taskService;
	private Project project;

	@Before
	public void setUp() throws Exception {
		taskService = new TaskService();
		project = new Project();
		project.setId(6);

	}

	@Test
	public void testGetTaskById() {
		Task task = new Task();
		task.setTitle("Sample Task");
		task.setDescription("Sample Task Description");
		task.setTaskPriority(TaskPriority.LOW);
		task.setTaskStatus(TaskStatus.DONE);
		task.setProject(project);

		long taskId = taskService.addTask(task);

		Optional<Task> retrievedTask = taskService.getTaskById(taskId);

		taskService.deleteTask(taskId);

		assertTrue(retrievedTask.isPresent());
		assertEquals("Sample Task", retrievedTask.get().getTitle());

	}

	@Test
	public void testGetAllTasks() {
		Task task1 = new Task();
		task1.setTitle("Test Task 1");
		task1.setDescription("Test Description 1");
		task1.setTaskPriority(TaskPriority.LOW);
		task1.setTaskStatus(TaskStatus.DOING);
		task1.setProject(project);

		Task task2 = new Task();
		task2.setTitle("Test Task 2");
		task2.setDescription("Test Description 2");
		task2.setTaskPriority(TaskPriority.MEDIUM);
		task2.setTaskStatus(TaskStatus.TODO);
		task2.setProject(project);

		long taskId1 = taskService.addTask(task1);
		long taskId2 = taskService.addTask(task2);

		List<Task> tasks = taskService.getAllTasks(project);

		assertNotNull(tasks);

		taskService.deleteTask(taskId1);
		taskService.deleteTask(taskId2);

		assertTrue(tasks.stream().anyMatch(t -> t.getTitle().equals("Test Task 1")));

	}

	@Test
	public void testAddTask() {
		Task task = new Task();
		task.setTitle("Test Task");
		task.setDescription("Test Task Description");
		task.setTaskPriority(TaskPriority.HIGH);
		task.setTaskStatus(TaskStatus.TODO);
		task.setProject(project);

		taskService.addTask(task);
		List<Task> tasks = taskService.getAllTasks(project);

		taskService.deleteTask(tasks.stream().filter(t -> t.getTitle().equals("Test Task")).findFirst().get().getId());

		assertFalse(tasks.isEmpty());
		assertTrue(tasks.stream().anyMatch(t -> t.getTitle().equals("Test Task")));

	}

	@Test
	public void testUpdateTask() {
		Task task = new Task();
		task.setTitle("Test Initial Task");
		task.setDescription("Test Initial Description");
		task.setTaskPriority(TaskPriority.MEDIUM);
		task.setTaskStatus(TaskStatus.DOING);
		task.setProject(project);

		long taskId = taskService.addTask(task);

		Task updatedTask = new Task();
		updatedTask.setTitle("Test Updated Task");
		updatedTask.setDescription("Test Updated Description");
		updatedTask.setTaskPriority(TaskPriority.HIGH);
		updatedTask.setTaskStatus(TaskStatus.DONE);

		taskService.updateTask(taskId, updatedTask);

		Optional<Task> retrievedTask = taskService.getTaskById(taskId);

		taskService.deleteTask(taskId);

		assertTrue(retrievedTask.isPresent());
		assertEquals("Test Updated Task", retrievedTask.get().getTitle());
		assertEquals(TaskPriority.HIGH, retrievedTask.get().getTaskPriority());

	}

	@Test
	public void testDeleteTask() {
		Task task = new Task();
		task.setTitle("Task to Delete");
		task.setDescription("Delete This Task");
		task.setTaskPriority(TaskPriority.LOW);
		task.setTaskStatus(TaskStatus.DOING);
		task.setProject(project);

		taskService.addTask(task);
		List<Task> tasks = taskService.getAllTasks(project);
		Task savedTask = tasks.stream().filter(t -> t.getTitle().equals("Task to Delete")).findFirst().get();

		taskService.deleteTask(savedTask.getId());

		Optional<Task> retrievedTask = taskService.getTaskById(savedTask.getId());

		assertFalse(retrievedTask.isPresent());
	}

}

package com.example.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	//private TodoService todoService;
	private TodoRepository todoRepository;

	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		//this.todoService = todoService;
		this.todoRepository = todoRepository;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedinUsername(model);
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);
		return "listTodos";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		// Setting default values
		Todo todo = new Todo(0, getLoggedinUsername(model), "", LocalDate.now().plusYears(1), false);
		// "todo" should match the modelAttribute present in todo.jsp
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) { 
		// "todo" in param should match the modelAttribute present in todo.jsp
		// BindingResult is use to bind the v	alidation result with the form

		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = getLoggedinUsername(model);
		
		// instead of binding values to the model map, bind them directly to the Todo
		//todoService.addTodo(getLoggedinUsername(model), todo.getDescription(), todo.getTargetDate(), false);
		
		todo.setUsername(username);
		todoRepository.save(todo);
		
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(ModelMap model, @RequestParam int id) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute(todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) { 

		if(result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedinUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
		
		//todoService.updateTodo(todo);
		
		return "redirect:list-todos";
	}
	
	private String getLoggedinUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
package com.sparta.soloproject2.todo;

import com.sparta.soloproject2.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoListResponseDTO {
	private UserDTO user;
	private List<TodoResponseDTO> todoList;

	public TodoListResponseDTO(UserDTO user, List<TodoResponseDTO> todoList) {
		this.user = user;
		this.todoList = todoList;
	}
}
